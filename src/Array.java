public class Array<E> {

    private E[] data;
    private int size;

    public Array(int capacity){
        data = (E[])new Object[capacity];
        size = 0;
    }

    public Array(){
        this(10);
    }

    public int getSize(){
        return size;
    }

    public int getCapacity(){
        return data.length;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    //向所有元素后
    public void addLast(E e){

//        if(size == data.length)
//            throw new IllegalArgumentException("AddLast failed. Array is full.");
//
//        data[size] = e;
//        size++;其实只要下面这样写就行了
        add(size, e);
    }

    public void addFirst(E e){
        add(0, e);
    }

    //在第index个位置插入一个新元素e
    public void add(int index, E e){

        if(index < 0 || index > size){
            throw new IllegalArgumentException("Add failed. Require index >= 0 || index <= size.");
        }

        if(size == data.length)
            resize(2 * data.length);

        for(int i = size - 1; i >= index; i--){
            data[i+1] = data[i];
        }
        data[index] = e;
        size++;
    }

    //从数组中删除index位置的元素，返回删除的元素
    public E remove(int index){

        if(index < 0 || index > size){
            throw new IllegalArgumentException("Remove failed. Require index >= 0 || index <= size.");
        }

        E ret = data[index];

        for(int i = index; i < size; i++){
            data[i] = data[i+1];
        }
        size--;//如果data[size]还存着一个对象的引用的话，就不会被Java的自动垃圾回收所回收
        data[size] = null;//这句话也不是必须的，如果存了其他值，那么原来的值也会被垃圾回收
        // 这种对象叫loitering objects，闲逛的游散的对象 != memory leak

        if(size == data.length / 2){
            resize(data.length / 2);
        }

        return ret;
    }

    public E removeFirst(){
        return remove(0);
    }

    public E removeLast(){
        return remove(size - 1);
    }

    //从数组中删除元素e
    public boolean removeElement(E e){
        int index = find(e);
        if(index != -1){
            remove(index);
            return true;
        }

        return false;
    }

    E get(int index){
        if(index < 0 || index >= size){
            throw new IllegalArgumentException("Get failed, Index is illegal.");
        }
        return data[index];
    }

    void set(int index, E e){
        if(index < 0 || index >= size) {
            throw new IllegalArgumentException("Get failed, Index is illegal.");
        }
        data[index] = e;
    }

    //查找数据中是否有元素e
    public boolean contains(E e){
        for(int i = 0; i < size; i++){
            if(data[i].equals(e)){
                return true;
            }
        }

        return false;
    }

    //查找数组中元素e所在的索引，如果不存在元素e，则返回-1
    public int find(E e){
        for(int i = 0; i < size; i++){
            if(data[i].equals(e)){
                return i;
            }
        }

        return -1;
    }

    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array:size = %d , capacity = %d\n", size, data.length));
        res.append('[');
        for(int i = 0; i < size; i++){
            res.append(data[i]);
            if(i != size - 1){
                res.append(", ");
            }
        }
        res.append(']');

        return res.toString();
    }

    private void resize(int newCapacity){
        E[] newData = (E[])new Object[newCapacity];
        for (int i = 0; i < size; i++){
            newData[i] = data[i];
        }
        data = newData;
    }
}
