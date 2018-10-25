ArraySort
改动方法： sortArray, 选择排序, descending order, 降序, 判断条件里的>换成<


ArraySort2:
Error: result is in ascending order 
改正方法：判断条件里的>换成<, 两个变量 small和pos 没有用到


Digital Count
改动方法：这里面到最后num直接是0，造成bug，需要用一个数来储存num和执行运算


Replace array values
改动方法：replaceValues, 若array长度为奇数全换为1偶数换为0, i<=len, j<=len改成 i<len, j<len


Reverse Array
Error: len start from back of the array, it can not add, out of boundary
改正方法1：len = len -1 ;


改正方法2：reverse array: arr[len - 1]改成arr[len-i-1], 并且去掉len+=1.

Remove Element


Print EvenOddPattern
Error: 两个for循环缺少大括号
改正方法：加入大括号


Manchester code 两个元素相同输出 0，不同输出 1
             改正方法： result=(a[i]==a[i-1])改成result=(a[i]!=a[i-1])，可以假设第一个element 之前的数字是0。
          
sumArray, 素组求和，sum = arr[i] 改为 sum += arr[i]
          
PrintPattern2, 输出 a ab abc abcd cout(ch++) 改为 cout(print++)，好像还有个编译错误
          

PrintPattern3: 1111括号错误
          
          
removeDuplicates, 报错是Index out of bound, 因为for循环里有 k<length, 下面却使用arr[k+1] 改为 k < length - 1
remove duplicates form unsorted array i+1
array 奇数偶数, for循环里, i+=2 改为i++
Insertion Sort
          
count Occurrence, 返回value在array中出现的次数, while里面最后加上 i++（error: TLE）
checkGrade, 成绩打分ABCD，判断输入的数在什么范围, 它上面是 （x >= 70) || (x < 80), 两个|| 改成 &&
arrayOperation < >反了(没看到更具体的分析)
selection sort, ascending order, arr[index_of_min] > arr[x] 改为 arr[y]


