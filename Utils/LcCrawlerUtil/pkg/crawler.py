import json
import os.path
import random
import re
import time
from typing import List
from urllib.parse import quote

import requests
import utils
import urllib3

urllib3.disable_warnings()

user_agent = (
    'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) '
    'Chrome/77.0.3865.120 Safari/537.36'
)
sub_folders = [
    str(i * 100).zfill(4) + '-' + str(i * 100 + 99).zfill(4) for i in range(100)
]
graph_url = 'https://leetcode.com/graphql'
difficulty = dict(Easy='简单', Medium='中等', Hard='困难')

"""
{
    "sub_folder":"2200-2299",
    "question_id":"2385",
    "frontend_question_id":"2237",
    "paid_only":true,
    "paid_only_cn":true,
    "category":"Algorithms",
    "url_cn":"https://leetcode.cn/problems/count-positions-on-street-with-required-brightness",
    "url_en":"https://leetcode.com/problems/count-positions-on-street-with-required-brightness",
    "relative_path_cn":"/solution/2200-2299/2237.Count%20Positions%20on%20Street%20With%20Required%20Brightness/README.md",
    "relative_path_en":"/solution/2200-2299/2237.Count%20Positions%20on%20Street%20With%20Required%20Brightness/README_EN.md",
    "title_cn":"Count Positions on Street With Required Brightness",
    "title_en":"Count Positions on Street With Required Brightness",
    "question_title_slug":"count-positions-on-street-with-required-brightness",
    "content_en":"Description",
    "content_cn":"问题描述[此处省略]",
    "tags_en":[
    ],
    "tags_cn":[
    ],
    "difficulty_en":"Medium",
    "difficulty_cn":"中等",
    "code_snippets":[
        {
            "lang":"C++",
            "langSlug":"cpp",
            "code":"class Solution {
public:
    int meetRequirement(int n, vector<vector<int>>& lights, vector<int>& requirement) {
        
    }
};",
            "__typename":"CodeSnippetNode"
        },
        {
            "lang":"Java",
            "langSlug":"java",
            "code":"class Solution {
    public int meetRequirement(int n, int[][] lights, int[] requirement) {
    }
}",
            "__typename":"CodeSnippetNode"
        }
    ],
    "md_table_row_cn":[
        "[2237](https://leetcode.cn/problems/count-positions-on-street-with-required-brightness)",
        "[Count Positions on Street With Required Brightness](/solution/2200-2299/2237.Count%20Positions%20on%20Street%20With%20Required%20Brightness/README.md)",
        "",
        "中等",
        "🔒"
    ],
    "md_table_row_en":[
        "[2237](https://leetcode.com/problems/count-positions-on-street-with-required-brightness)",
        "[Count Positions on Street With Required Brightness](/solution/2200-2299/2237.Count%20Positions%20on%20Street%20With%20Required%20Brightness/README_EN.md)",
        "",
        "Medium",
        "🔒"
    ]
}
"""


class Crawler:
    def __init__(self, cookie: str):
        self.cookie_cn = ''
        self.cookie = cookie
        self.session = requests.session()
        self.raw_data = {}
        self.contest = {}
        if os.path.exists('./contest.json'):
            with open('contest.json', 'r', encoding='utf-8') as f:
                self.contest = json.loads(f.read())

    def get_all_questions(self) -> List:
        """获取所有题目"""
        headers = {
            'accept': 'application/json, text/javascript, */*; q=0.01',
            'content-type': 'application/json',
            'user-agent': user_agent,
            'x-requested-with': 'XMLHttpRequest',
            'cookie': self.cookie,
        }
        resp = self.session.get(
            url='https://leetcode.com/api/problems/all/',
            headers=headers,
            allow_redirects=False,
            timeout=10,
            verify=False,
        )
        return resp.json()['stat_status_pairs']

    def _get_question_detail(self, question_title_slug) -> dict:
        """获取题目详情"""
        form_data = {
            'operationName': 'globalData',
            'query': 'query globalData {\n  feature {\n    questionTranslation\n    subscription\n    signUp\n    '
                     'discuss\n    mockInterview\n    contest\n    store\n    book\n    chinaProblemDiscuss\n    '
                     'socialProviders\n    studentFooter\n    cnJobs\n    enableLsp\n    enableWs\n    '
                     'enableDebugger\n    enableDebuggerAdmin\n    enableDarkMode\n    tasks\n    '
                     'leetbook\n    __typename\n  }\n  userStatus {\n    isSignedIn\n    isAdmin\n    '
                     'isStaff\n    isSuperuser\n    isTranslator\n    isPremium\n    isVerified\n    '
                     'isPhoneVerified\n    isWechatVerified\n    checkedInToday\n    username\n    '
                     'realName\n    userSlug\n    groups\n    avatar\n    optedIn\n    '
                     'requestRegion\n    region\n    activeSessionId\n    permissions\n    notificationStatus {\n      '
                     'lastModified\n      numUnread\n      __typename\n    }\n    completedFeatureGuides\n    '
                     'useTranslation\n    accountStatus {\n      isFrozen\n      inactiveAfter\n      __typename\n    '
                     '}\n    __typename\n  }\n  siteRegion\n  chinaHost\n  websocketUrl\n  userBannedInfo {\n    '
                     'bannedData {\n      endAt\n      bannedType\n      __typename\n    }\n    __typename\n  }\n}\n',
            'variables': {},
        }
        headers = {
            'User-Agent': user_agent,
            'Connection': 'keep-alive',
            'Content-Type': 'application/json',
            'Referer': 'https://leetcode.com/problems/' + question_title_slug,
            'cookie': self.cookie,
        }
        # self.session.post(
        #     url=graph_url,
        #     data=json.dumps(form_data),
        #     headers=headers,
        #     timeout=10,
        #     verify=False,
        # )
        form_data = {
            'operationName': 'questionData',
            'variables': {'titleSlug': question_title_slug},
            'query': 'query questionData($titleSlug: String!) {\n  question(titleSlug: $titleSlug) {\n    '
                     'questionId\n    questionFrontendId\n    boundTopicId\n    title\n    titleSlug\n    content\n   '
                     ' translatedTitle\n    translatedContent\n    isPaidOnly\n    difficulty\n    likes\n    '
                     'dislikes\n    isLiked\n    similarQuestions\n    exampleTestcases\n    categoryTitle\n    '
                     'contributors {\n      username\n      profileUrl\n      avatarUrl\n      __typename\n    }\n    '
                     'topicTags {\n      name\n      slug\n      translatedName\n      __typename\n    }\n    '
                     'companyTagStats\n    codeSnippets {\n      lang\n      langSlug\n      code\n      __typename\n '
                     '   }\n    stats\n    hints\n    solution {\n      id\n      canSeeDetail\n      paidOnly\n      '
                     'hasVideoSolution\n      paidOnlyVideo\n      __typename\n    }\n    status\n    '
                     'sampleTestCase\n    metaData\n    judgerAvailable\n    judgeType\n    mysqlSchemas\n    '
                     'enableRunCode\n    enableTestMode\n    enableDebugger\n    envInfo\n    libraryUrl\n    '
                     'adminUrl\n    challengeQuestion {\n      id\n      date\n      incompleteChallengeCount\n      '
                     'streakCount\n      type\n      __typename\n    }\n    __typename\n  }\n}\n',
        }
        # get question detail
        resp = self.session.post(
            url=graph_url,
            data=json.dumps(form_data).encode('utf-8'),
            headers=headers,
            timeout=10,
            verify=False,
        )
        res = resp.json()
        return res['data']['question']

    def get_question_detail(self, question_title_slug, retry_times=0):

        time.sleep(0.5 + random.random())
        for _ in range(retry_times + 1):
            try:
                question_detail = self._get_question_detail(question_title_slug)
                if question_detail:
                    return question_detail
            except Exception as e:
                print(f'error:{str(e)}')
            time.sleep(random.choice(range(2, 5)))
        return None

    def process_question(self, question: dict) -> dict:
        question_title_slug = question['stat']['question__title_slug']
        question_detail = self.get_question_detail(question_title_slug, retry_times=5)
        if not question_detail:
            return {}
        url_cn = f'https://leetcode.cn/problems/{question_title_slug}'
        url_en = f'https://leetcode.com/problems/{question_title_slug}'
        frontend_question_id = str(question['stat']['frontend_question_id']).zfill(4)
        self.raw_data[frontend_question_id] = question_detail
        no = int(frontend_question_id) // 100
        question_title_en = question['stat']['question__title']
        question_title_en = re.sub(r'[\\/:*?"<>|]', '', question_title_en).strip()
        path = f'/solution/{sub_folders[no]}/{frontend_question_id}.{quote(question_title_en)}/README.md'

        print(f'{frontend_question_id}. {question_title_en}')
        topic_tags = question_detail.get('topicTags')

        item = {
            'sub_folder': sub_folders[no],
            'question_id': str(question['stat']['question_id']).zfill(4),
            'frontend_question_id': frontend_question_id,
            'paid_only': question['paid_only'],
            'paid_only_cn': question_detail.get('isPaidOnly'),
            # Shell Database Algorithms Concurrency
            'category': question_detail.get('categoryTitle'),
            'url_cn': url_cn,
            'url_en': url_en,
            'relative_path_cn': path_cn,
            'relative_path_en': path_en,
            'title_cn': question_detail.get('translatedTitle')
                        or question_title_en
                        or '',
            'title_en': question_title_en or '',
            'question_title_slug': question_title_slug,
            'content_en': question_detail.get('content'),
            'content_cn': question_detail.get('translatedContent')
                          or question_detail.get('content'),
            'tags_en': [e['name'] for e in topic_tags if e['name']] or [],
            'tags_cn': [e['translatedName'] for e in topic_tags if e['translatedName']]
                       or [],
            'difficulty_en': question_detail.get('difficulty'),
            'difficulty_cn': difficulty.get(question_detail.get('difficulty')),
            'code_snippets': question_detail.get('codeSnippets') or [],
        }

        col1_cn = frontend_question_id
        col2_cn = (
            f'[{item["title_cn"]}]({path_cn})'
            if item["title_cn"]
            else f'[{item["title_en"]}]({path_en})'
        )
        col3_cn = ','.join([f'`{tag}`' for tag in item['tags_cn']])
        col3_cn = '' if (col3_cn == 'None' or not col3_cn) else col3_cn
        col4_cn = item['difficulty_cn']
        col5_cn = '🔒' if item['paid_only_cn'] else ''
        if not col5_cn and question_title_slug in self.contest:
            col5_cn = self.contest[question_title_slug]['contest_title']

        col1_en = frontend_question_id
        col2_en = f'[{item["title_en"]}]({path_en})'
        col3_en = ','.join([f'`{tag}`' for tag in item['tags_en']])
        col3_en = '' if (col3_en == 'None' or not col3_en) else col3_en
        col4_en = item['difficulty_en']
        col5_en = '🔒' if item['paid_only'] else ''
        if not col5_en and question_title_slug in self.contest:
            col5_en = self.contest[question_title_slug]['contest_title_en']

        item['md_table_row_cn'] = [col1_cn, col2_cn, col3_cn, col4_cn, col5_cn]
        item['md_table_row_en'] = [col1_en, col2_en, col3_en, col4_en, col5_en]
        return item

    def save(self):
        with open('./raw.json', 'w', encoding='utf-8') as f:
            f.write(json.dumps(self.raw_data))

    def run(self):
        questions = self.get_all_questions()
        details = [self.process_question(question) for question in questions]
        failed_questions = [
            question for i, question in enumerate(questions) if not details[i]
        ]
        details += [self.process_question(question) for question in failed_questions]
        details = [detail for detail in details if detail]
        self.save()
        return details


crawler = Crawler(
    "gr_user_id=c141d545-d594-451d-a1f9-4ba6b7085924; 87b5a3c3f1a55520_gr_last_sent_cs1=ZaZahui528; _gcl_au=1.1.815796698.1655968513; intercom-id-pq9rak4o=956eef5d-bb11-429b-a00f-9a3de491303d; _ga=GA1.2.1185842239.1655966572; _ga_DKXQ03QCVK=GS1.1.1655968513.1.0.1655968783.60; __stripe_mid=3046755c-8059-4336-8af1-ccb0b273264ff504e2; _gid=GA1.2.754222890.1659863172; LEETCODE_SESSION=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJfYXV0aF91c2VyX2lkIjoiNDg5MzEwIiwiX2F1dGhfdXNlcl9iYWNrZW5kIjoiYWxsYXV0aC5hY2NvdW50LmF1dGhfYmFja2VuZHMuQXV0aGVudGljYXRpb25CYWNrZW5kIiwiX2F1dGhfdXNlcl9oYXNoIjoiY2JkNTg3YWFhMDE0NGViODJkNTUyMzhhNDc2MDU1ZjA2MDdiNmZmMyIsImlkIjo0ODkzMTAsImVtYWlsIjoiNDU0MTY2NDQ4QHFxLmNvbSIsInVzZXJuYW1lIjoiWmFaYWh1aTUyOCIsInVzZXJfc2x1ZyI6IlphWmFodWk1MjgiLCJhdmF0YXIiOiJodHRwczovL2Fzc2V0cy5sZWV0Y29kZS5jb20vdXNlcnMveHVjaGVuZ3l1bi9hdmF0YXJfMTU3MjQxMzAwOS5wbmciLCJyZWZyZXNoZWRfYXQiOjE2NTk5NDUwMTQsImlwIjoiNzYuMTIxLjMuODYiLCJpZGVudGl0eSI6IjVkYjYwNjgwNmEyNzQxZWU3NTQ3YWUwNDJjYjhiZmU0Iiwic2Vzc2lvbl9pZCI6MjQxMTg2OTIsIl9zZXNzaW9uX2V4cGlyeSI6MTIwOTYwMH0.t0f3PZCSn34ysdrMopOq_hs6PcDO5etjdsvypHVIDF0; 87b5a3c3f1a55520_gr_session_id=5c315607-3991-47d8-9723-94a9d6d6a649; 87b5a3c3f1a55520_gr_last_sent_sid_with_cs1=5c315607-3991-47d8-9723-94a9d6d6a649; 87b5a3c3f1a55520_gr_session_id_5c315607-3991-47d8-9723-94a9d6d6a649=true; 87b5a3c3f1a55520_gr_cs1=ZaZahui528")
res = crawler.get_question_detail("two-sum")
print(utils.format_json(res))
