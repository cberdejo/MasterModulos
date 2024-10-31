"""
Write a function that accepts two dictionaries and merges them together such that the resulting dictionary always retain the greater value among mappings with common keys.

E.g.:

 dict1 = {'bananas': 7, 'apples': 3, 'pears': 14}
 dict2 = {'bananas': 3, 'apples': 6, 'grapes': 9}
 merge_max_mappings(dict1, dict2)
{'bananas': 7, 'apples': 6, 'pears': 14, 'grapes': 9}
"""

def merge_max_mappings(dict1:dict, dict2:dict ) -> dict:

   if len(dict1) > len(dict2):
    merged = dict1.copy()
    other = dict2.copy()
   else:
    merged = dict2.copy()
    other = dict1.copy()

    for key in other:
        if key in merged:
            merged[key] = max(merged[key], other[key])
        else:
            merged[key] = other[key]
    return merged



test_merge_max_mapings()
test_merge_max_mapings_empty_dict()