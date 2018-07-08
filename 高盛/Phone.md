04/02 Mon  高盛面经

一个三姐, 上来寒暄一下做了个自我介绍, 直接上题 LC 49. 心中窃喜, 直接上时间O(n)最优解. 跟原来不同的是要调用在functial interface(JAVA8 新特性)里面的method，test case也包含在method里面. 
然而我对这个特性并不了解，于是问三姐是否可以不调用这个interface， 直接自己建main函数和test case. 三姐同意，于是自己建了main函数写了几个test case，可惜没有一遍bug free，改了一次才通过。 

Follow up：三姐说能不能优化空间复杂度，我想这题最优解空间也要O (n), 如何优化， 再问之，嫌我建的char[26]占空间, 如果26个字母没有全用到，只用了几个,建26个字母岂不是多余了(我去。。。).  那我就说用hashMap了。 

Follow up2: 问这题还有没有别的方法，我说有, 就是sort String[] 中的每一个element，看他们有没有相同的key，但是这种方法没有我的好，会有N* mlogm 的复杂度，我的方法是linear的，三姐说嗯嗯。 然后就进入最后的我问你答时间。