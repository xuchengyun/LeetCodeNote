import json


def format_json(obj):
    return json.dumps(obj, indent=4)


def convert_to_camel(s):
    return ''.join([x.capitalize() for x in s.split()])
