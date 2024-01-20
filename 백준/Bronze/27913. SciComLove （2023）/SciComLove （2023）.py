N, Q = map(int, input().split())

dic = {1:True, 2:False, 3:False, 4:True, 5:False, 6:False, 7:True, 8:False, 9:False, 10:False}
big = 0
small = 0
big = N//10*3
M = N % 10
for i in range(1, M+1):
    if dic[i] is True: big += 1
xi = 0
for i in range(Q):
    index = int(input())
    while(index > xi):
        xi += 1
        M += 1
        M %= 10
        if dic[M] is True: big -= 1
        else: big += 1
        print(big)


