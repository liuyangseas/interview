早上11点去前台check in。等着被安排到一间小房间。有一台电脑，对面做了两个人。一个印度模样的小哥，但英语很好，一个hiring manager. 问题基本上都是小哥再问。问题都是事先准备好的。问了有关c# 和OOD的知识，如abstract和interface. access modifer(protect, internal). static的作用，readonly。 Extension method， delegate， lambda, LINQ.其中有一道题没回答出来，他记了一点笔记。
之后是coding题，第一题是给定一个数，判断是不是质数。秒了。写了main函数，跑了几个测试例子一遍通过。
之后小哥又考了道更简单的，要求给一个string，转换成int。好像是LC上的一道easy题，也秒了。
可能写得太快了，小哥看了看表，问我知不知道单例模式，好处是什么，我都回答上来了，然后让我写。这确实有点懵逼，确实不记得是怎么写的了，根据自己的感觉写的，小哥说好像不对，然后又问我你怎么不让外部new这个static，我当时已经完全懵逼了。到最后小哥跟我说其实你写得基本上都对的，但是声明实例的时候应该用private static，而不是public static,不然单例的意义是什么呢？唉 当时气氛有点尴尬，我就觉得大好的开局可能要悲剧了。。。
之后Hiring manager打破了尴尬，跟小哥说，我们主要招做web的，问他有没有web方面的题要考我。小哥说有，把那张问题纸翻了过来，又问了很多web api和mvc的问题，其中有一个问题是你每次做depency injection的时候引用什么包的，我确实不记得了，他跟我说叫OIDC, 问我OIDC怎么改service的life span. 我说好像是可以在startup.cs里面register的时候改addSingleton或者addScope。他点了点头，剩下的问题都很常规，我也都回答上来了。
之后Hiring manager简单问了问为啥要离开，多久可以离职加入新公司之类的惯性问题，把我交给了HR，并说HR会跟我follow up之后的事情。HR带我参观了公司，感觉公司的装修很像一个缩小版的yelp。后来走的时候碰到了我的同学，她说今天中午正好有午饭，就带我去餐厅了，其实也就是从外面订的外国人爱吃的那种冷冷的lunch box。
感觉问题都很简单，这个面试难度感觉更像是在招General的Software Engineer. 但是singlton没写出来确实很不应该，最近也确实没有准备过设计模式。。。而且根本想不到会考。。