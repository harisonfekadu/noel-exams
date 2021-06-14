package logic.dataStrucures.structureModules;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logic.errorManagment.NodeNotFound;

public class LinkedList<Node> {
    //  Fields
    public LinkedList<Node> next;
    public Node node;
    public int index;

    //  Constructors
    public LinkedList(int index,Node  node){
        this.node=node;
        this.index=index;
        this.next=null;
    }
    public LinkedList(Node  node){
        this.node=node;
        this.index=0;
        this.next=null;
    }
    public LinkedList(){
        this.node=null;
        this.index=0;
        this.next=null;
    }
    //  Methods
    public int size(){
        LinkedList<Node> list=this;
        if(this.node==null){
            return 0;
        }
        int i;
        for(i=0;list!=null;i++){
            list=list.next;
        }
        return i;
    }
    public LinkedList<Node> addNode(LinkedList<Node> list, LinkedList<Node> newList){
        if (list==null){
            list=newList;
            return list;
        } else if(list.next==null){
            list.next=newList;
            return list;
        } else {
            list.next.next = list.addNode(list.next.next, newList);
            return list;
        }
    }
    public LinkedList<Node> addNode(LinkedList<Node> list, Node node){
        LinkedList<Node> newList=new LinkedList<Node>(0,node);
        return addNode(list,newList);
    }
    public void add(Node node){
        if(this.node==null){
            this.node=node;
        } else {
            this.next=this.addNode(this.next,node);
        }
        this.sequenceIndex();
    }
    public Node get(LinkedList<Node> list, int index)throws NodeNotFound {
        int size=list.size();
        for (int i=0;i<size;i++){
            if(list.index==index){
                return list.node;
            }
            list=list.next;
        }
        throw new NodeNotFound(index);
    }
    public Node get(int index)throws NodeNotFound {
        try {
            return this.get(this,index);
        } catch (NodeNotFound e){
            throw new NodeNotFound(e.getIndex());
        }
    }
    @Deprecated
    public LinkedList<Node> trim(LinkedList<Node> list, int index, int size)throws NodeNotFound {
        LinkedList<Node> newList=list;
        if(index>=size){
            throw new NodeNotFound(index);
        } else if (index==newList.next.index){
            newList.next=null;
            list=newList;
            return list;
        } else {
            newList.next=newList.trim(newList.next,index,size);
            return newList;
        }
    }
    public void clear(){
        this.node=null;
        this.next=null;
        this.index=0;
    }
    public Node remove(int index){
        int i=1;
        LinkedList<Node> list;
        Node deletedNode=null;
        if (index==0){
            list=new LinkedList<Node>(this.next.node);
            i++;
        } else {
            list=new LinkedList<Node>(this.node);
        }
        try {
            for(;i<this.size();i++){
                if(index==i){
                    deletedNode=this.get(i);
                    continue;
                }
                list.add(this.get(i));
            }
        } catch (NodeNotFound e){
            // --
        }
        this.index=list.index;
        this.node=list.node;
        this.next=list.next;
        return deletedNode;
    }
    public LinkedList<Node> sequenceIndex(LinkedList<Node> list){
        LinkedList<Node> newList=new LinkedList<Node>(0,list.node);
        int size=list.size();
        for(int i=1;i<size;i++){
            list=list.next;
            newList.addNode(newList,new LinkedList<Node>(i,list.node));
        }
        return newList;
    }
    public void sequenceIndex(){
        LinkedList<Node> temp=this.sequenceIndex(this);
        this.index=temp.index;
        this.node=temp.node;
        this.next=temp.next;
    }
    public boolean isEmpty(){
       return this.node==null && this.next==null;
    }
    public ObservableList<Node> toObservableList(){
        ObservableList<Node> list= FXCollections.observableArrayList();
        try {
            for(int i=0;i<this.size();i++){
                list.add(this.get(i));
            }
        } catch (Exception e){
            System.out.println(e.toString());
        }
        return list;
    }

    @Override
    public String toString(){
        return "index: "+index;
    }
}
/*public void trim(int index)throws NodeNotFound{
        if(index==0){
            this.index=0;
            this.node=null;
            this.next=null;
        } else {
            try {

                LinkedList<Node> temp=this.trim(this,index,this.size());
                this.index=temp.index;
                this.node=temp.node;
                this.next=temp.next;
            } catch (NodeNotFound e){
                throw new NodeNotFound(e.getIndex());
            }
        }
    }*/
