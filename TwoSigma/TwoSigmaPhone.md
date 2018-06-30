# interview

# What is a hash table? When to use? Why use it?

Given a (key, value) pair, hash table is data structure which converts the key to an index and then store the value somewhere using that index.

Generally, we can think of it a as a mapping from key to value.

Hash function generates index of buckets or slots using key.

Ideally, it maps key to a unique bucket.  The best situation is it provides a uniform distribution of hash values..

关于Hash Function，我们最常见的方法就是取模 %，但是这个问题在于n变成n+1的时候，每个key % n 和 n+1结果基本上都不一样，会发声数据迁移
所以我们引出了Consistent Hashing

更加简单的版本： 将key模一个很大的数 如360，将360分配给n个机器，每个机器负责一段区间

更加优化的版本： 将整个Hash区间看作一个环，任何一台机器和任何一个数据都看成hash上的一个点，那么怎么定哪个数据在哪个机器呢？
将环的大小变为2的64次方，引入Micros shards、virtual nodes的概念，一台实体机器对应1000个点，每新加入一个机器，我们就随机撒1000个点。对于每个key计算hash值，对应一个点，顺时针找到一个机器。

Operations cost:
- Constant, amortized O(1)
- worst case O(n) for search & deletion (add flag to fix it)

Size:
- power of 2
- prime number, good for even poorly designed hash function

Resizing (resize + insert again O(n) in average (n/2)
- Load factor = (# of entries) / (# of buckets)
- keep size within a good range (not too many collision, not too large wasted memory)

Open Addressing (close hashing) is method of collision resolution in hash tables. It’s resolved by probing. Probing includes linear probing, quadratic probing and double hashing.. 

Linear probing:
Search: Find next available slot. 
Insert:
Delete (Trouble):
- Iterate through the following slots until find an empty slot or move one back to this slot (recursively)
- Or use flag

Problems happen when there’s primary clustering. It means two records are mapped to the same index which causes one of them to move. And afterwards collisions increases during inserting more key value pair into hash table. A tendency that a collision will cause more nearby collisions.

With poorly designed hash function, i.e., hash function that would not make inputs uniformly distributed, linear probing could be slower than quadratic probing and double hashing.


Someone implement hash table and it is slow, why?
- poor hash function
- bad open addressing strategy

Use hash table to store data, but there is much more data than the machine's RAM, how to deal with that?     add one more machine, rehash and reconstruct the hash table