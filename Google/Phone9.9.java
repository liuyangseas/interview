public class SortedListList {

List<List<Integer>> newList;

Queue<Integer> queue;

 public SortedListList(List<List<Integer>> input) {

       newList=new List<List<Integer>>(input);

       Int len=input.size();

       queue=new PriorityQueue<Integer>(new Comparator<Integer>(){

          Public int compare(Integer i1,Integer i2){

             Return i1-i2;

            }

       });

     for(int i=0;i<len;i++){

        List<Integer> temp=newList.get(i);

         if(temp==null)

           continue;

        Int size=temp.size();

        for(int j=0;j<size;j++){

             queue.offer(temp.get(j));

         }

   }

}


 // Smallest integer from input which has not yet been returned

 public int next() {

   try{

      if(hasNext()){

        Return queue.poll();

      }

    //case

       Throw new EmptyException();

    }

}


 // if such an integer exists

 public boolean hasNext() {

     if(queue.isEmpty()){

        Return false;

      }

      Return true;

 }

}




Node {

 int id;

 int parentId;

 int weight;

}


id | parent | weight

10 |  0     | 2

11 | 10     | 4

12 | 10     | 3

15 | 11     | 5


 (10)

 /  \

(11) (12)

/

(15)


//input 12, return 3

//input 10, return 14

//input 11, return 9

//list is empty. Return 0?

//if startid is not exist? Return 0?

Public int res=0;

Public int getSubtreeWeight(List<Node> nodes, int startId) {

   if(nodes==null||nodes.size()==0)

   Return 0;

  for(int i=0;i<nodes.size();i++){

     if(nodes.get(i).id=startid)

      res+=map.get(startId).weight;

  }

   List<Node> sublist=new List<Node>();

   for(int i=0;i<nodes.size();i++){

     if(nodes.get(i).parentId==startId){

       res=res+getSubtreeWeight( nodes,nodes.get(i).id);

     }

   }

 Reutrn res;


}
