import collections
import json
import os
import re
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


if __name__ == '__main__':
    cookie = \
    'gr_user_id=c141d545-d594-451d-a1f9-4ba6b7085924; 87b5a3c3f1a55520_gr_last_sent_cs1=ZaZahui528; _gcl_au=1.1.815796698.1655968513; intercom-id-pq9rak4o=956eef5d-bb11-429b-a00f-9a3de491303d; _ga=GA1.2.1185842239.1655966572; _ga_DKXQ03QCVK=GS1.1.1655968513.1.0.1655968783.60; __stripe_mid=3046755c-8059-4336-8af1-ccb0b273264ff504e2; _gid=GA1.2.535106172.1661601492; NEW_PROBLEMLIST_PAGE=1; __atuvc=0%7C30%2C5%7C31%2C12%7C32%2C4%7C33%2C5%7C34; csrftoken=iM3WiLEv3gnAFX3kqLEt8Y9SRte5Zia3SKz2HCidypRm4cUiAwomBa89J72MjRTi; messages="d78c8971dab6281c5c772a5c27e175199478f805$[[\"__json_message\"\0540\05425\054\"You have signed out.\"]\054[\"__json_message\"\0540\05425\054\"Successfully signed in as ZaZahui528.\"]\054[\"__json_message\"\0540\05425\054\"You have signed out.\"]\054[\"__json_message\"\0540\05425\054\"Successfully signed in as ZaZahui528.\"]\054[\"__json_message\"\0540\05425\054\"You have signed out.\"]\054[\"__json_message\"\0540\05425\054\"Successfully signed in as ZaZahui528.\"]]"; LEETCODE_SESSION=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJfYXV0aF91c2VyX2lkIjoiNDg5MzEwIiwiX2F1dGhfdXNlcl9iYWNrZW5kIjoiYWxsYXV0aC5hY2NvdW50LmF1dGhfYmFja2VuZHMuQXV0aGVudGljYXRpb25CYWNrZW5kIiwiX2F1dGhfdXNlcl9oYXNoIjoiY2JkNTg3YWFhMDE0NGViODJkNTUyMzhhNDc2MDU1ZjA2MDdiNmZmMyIsImlkIjo0ODkzMTAsImVtYWlsIjoiNDU0MTY2NDQ4QHFxLmNvbSIsInVzZXJuYW1lIjoiWmFaYWh1aTUyOCIsInVzZXJfc2x1ZyI6IlphWmFodWk1MjgiLCJhdmF0YXIiOiJodHRwczovL2Fzc2V0cy5sZWV0Y29kZS5jb20vdXNlcnMveHVjaGVuZ3l1bi9hdmF0YXJfMTU3MjQxMzAwOS5wbmciLCJyZWZyZXNoZWRfYXQiOjE2NjE3MjM0MzEsImlwIjoiNzYuMTIxLjMuODYiLCJpZGVudGl0eSI6Ijk3ZjgwNzQ2OWJjMWUwMzkwZjRiNGE3OTNiMTRkMmViIiwic2Vzc2lvbl9pZCI6MjY4MDc2MjQsIl9zZXNzaW9uX2V4cGlyeSI6MTIwOTYwMH0.ILb_ui7MeX-zCNg99Yzrn7AnsxHJEDxxVPKepfP_IIM; 87b5a3c3f1a55520_gr_session_id=4134921f-0c19-4305-b90c-6afc71edaebf; 87b5a3c3f1a55520_gr_last_sent_sid_with_cs1=4134921f-0c19-4305-b90c-6afc71edaebf; 87b5a3c3f1a55520_gr_session_id_4134921f-0c19-4305-b90c-6afc71edaebf=true; _gat=1; 87b5a3c3f1a55520_gr_cs1=ZaZahui528; c_a_u="WmFaYWh1aTUyOA==:1oSZvb:ts6OkGFS8I7Owmmc2X9JNJGUtBg'
    question_title = 'Number of Islands'
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
