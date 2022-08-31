import collections
import json
import os
import re
import configparser
from urllib.parse import quote, unquote
from os.path import exists

from crawler import Crawler

# load templates
with open('./readme_template.md', 'r', encoding='utf-8') as f:
    readme_template = f.read()
with open('./problem_readme_template.md', 'r', encoding='utf-8') as f:
    problem_readme = f.read()


# with open('./problem_readme_template_en.md', 'r', encoding='utf-8') as f:
#     problem_readme_en = f.read()
# with open('./sql_problem_readme_template.md', 'r', encoding='utf-8') as f:
#     sql_readme_cn = f.read()
# with open('./sql_problem_readme_template_en.md', 'r', encoding='utf-8') as f:
#     sql_readme_en = f.read()
# with open('./bash_problem_readme_template.md', 'r', encoding='utf-8') as f:
#     bash_readme_cn = f.read()
# with open('./bash_problem_readme_template_en.md', 'r', encoding='utf-8') as f:
#     bash_readme_en = f.read()


def select_templates(category):
    #TODO
    # if category == 'Shell':
    #     return [bash_readme_cn, bash_readme_en]
    # if category == 'Database':
    #     return [sql_readme_cn, sql_readme_en]
    # return [problem_readme_cn, problem_readme_en]
    return problem_readme


def generate_readme_root(result):
    md_table_en = [item['md_table_row_en'] for item in result]

    # generate README_EN.md
    items = []
    table_en = '\n|  #  |  Solution  |  Tags  |  Difficulty  |  Remark |\n| --- | --- | --- | --- | --- |'
    readme = ''
    cur_items = []
    if exists('../../../LCQuestions/Solutions/README.md'):
        with open('../../../LCQuestions/Solutions/README.md', 'r', encoding='utf-8') as f:
            readme = f.read()
        q_list = re.findall('\|\s*\d{4}\s*\|.*\n', readme)
        cur_items = [[k.strip() for k in x.rstrip('\n').strip('|').split('|')] for x in q_list]
        cur_items = __update(md_table_en, cur_items)
    else:
        cur_items = md_table_en
    for item in sorted(cur_items, key=lambda x: x[0]):
        items.append(
            f'\n|  {item[0]}  |  {item[1]}  |  {item[2]}  |  {item[3]}  |  {item[4]}  |'
        )
    table_en += ''.join(items)
    readme = readme_template.format(table_en)
    # script_dir = os.path.dirname(__file__)
    with open('../../../LCQuestions/Solutions/README.md', 'w+', encoding='utf-8') as f:
        f.write(readme)


def generate_question_readme(result):
    for item in result:
        if not item['content']:
            continue
        path = (
            f'../../../LCQuestions/Solutions/{item["sub_folder"]}/_{item["frontend_question_id"]}_{item["title_camel"]}'
        )
        path = path.replace(":", " ")
        if not os.path.isdir(path):
            os.makedirs(path)

        # choose the readme template
        category = item['category']
        readme_template = select_templates(category)

        # generate lc-en problem readme
        with open(f'{path}/README.md', 'w+', encoding='utf-8') as f2:
            f2.write(
                readme_template.format(
                    int(item['frontend_question_id']),
                    item["title_en"],
                    item['url'],
                    item['content'],
                )
            )


def refresh(result):
    """update problems"""
    pattern = re.compile("src=\"(.*?)\"")

    for question in result:
        front_question_id = question['frontend_question_id']
        print(front_question_id)
        title_en = question['title_en']

        path = question['relative_path']
        # update title
        with open(path, 'r', encoding='utf-8') as f2:
            en_content = f2.read()
        i = en_content.index('. ')
        j = en_content.index(']')
        en_content = en_content.replace(en_content[i + 2: j], title_en)

        # update question content
        # .   # match any character except newlines
        # *   # zero or more times
        # ?   # matching as few characters as possible
        old_content = re.search(
            "## Description(.*?)## Solutions", en_content, re.S).group(1)

        en_content = en_content.replace(
            old_content, "\n\n" + question['content'] + "\n\n"
        ).replace("\n\n    <ul>", "\n    <ul>")

        with open(path, 'w', encoding='utf-8') as f2:
            f2.write(en_content)


def save(result):
    with open('./result.json', 'w', encoding='utf-8') as f:
        f.write(json.dumps(result))


def __update(cur, new):
    grouped_dict = {}
    for x in cur:
        grouped_dict[x[0]] = x
    for x in new:
        grouped_dict[x[0]] = x
    grouped_list = []
    for a, l in grouped_dict.items():
        grouped_list.append(l)

    return grouped_list

# def update_root_readme():
#     with open('../../../LCQuestions/Solutions/README.md', 'r', encoding='utf-8') as f:
#         readme = f.read()
#
#     with open('../../../README.md', 'w+', encoding='utf-8') as f:
#         f.write(readme)


if __name__ == '__main__':
    configParser = configparser.RawConfigParser()
    configParser.read('config.txt')
    cookie = configParser.get("Cookies", "cookie")
    question_title = 'Pacific Atlantic Water Flow'
    crawler = Crawler(cookie)
    res = crawler.run(question_title)
    save(res)

    with open('./result.json', 'r', encoding='utf-8') as f:
        res = f.read()
        res = json.loads(res)
    #
    generate_readme_root(res)
    generate_question_readme(res)
    refresh(res)
    # update_root_readme()
