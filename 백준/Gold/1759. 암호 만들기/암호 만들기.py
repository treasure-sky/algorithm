from itertools import combinations

L, C = map(int, input().split())
input_arr = list(map(str, input().split()))

# 입력받은 문자 사전식 정렬
input_arr.sort()

# 모음 set 정의
vowel_set = {'a', 'e', 'i', 'o', 'u'}

res_arr = []

# combinations 는 tuple을 반환 하는 iterator을 반환
for pw_case in combinations(input_arr, L):
    # 최소 한 개의 모음, 최소 두 개의 자음 조건을 만족하는 pw_case만 res_arr에 추가
    if len(set(pw_case) & vowel_set) > 0 and len(set(pw_case) - vowel_set) > 1 :
        res_arr.append(pw_case)

# pw는 tuple형태로, 출력 형식에 맞게 가공
for pw in res_arr:
    for word in pw:
        print(word, end='')
    print("")