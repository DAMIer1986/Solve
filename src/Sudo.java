import java.util.*;

/**
 * @Author: wangxu [83922113@qq.com]
 * @Despriction:
 * @CreateOn: 18:10 2018-05-25
 */
public class Sudo {

    private int[] qusBox = new int[]{
            0,9,3,0,0,1,0,4,8,
            0,0,0,0,0,4,0,1,0,
            8,0,0,0,7,0,5,6,0,
            0,0,0,7,0,5,0,3,0,
            0,0,0,0,1,0,0,0,0,
            0,6,0,2,0,8,0,0,0,
            0,3,6,0,4,0,0,0,5,
            0,5,0,9,0,0,0,0,0,
            1,8,0,5,0,0,3,9,0
    };

    private Map<Integer,List<Integer>> readlyMap;

    private List<Integer> readlyList;

    public static void main(String[] args){
        Sudo m = new Sudo();
        long start = System.currentTimeMillis();
        m.getReadly(m.qusBox);
        if(m.readlyList.size() > 0){
            m.getResult(m.qusBox,m.readlyList.get(0));
        }
        for(int i = 0;i < m.qusBox.length;i++){
            if(i%9 == 0){
                System.out.println();
            }
            System.out.print("|" + m.qusBox[i] + "|");
        }
        System.out.println();
        System.out.println(System.currentTimeMillis() - start);
    }

    private boolean getResult(int[] box,int index){
        List<Integer> list = this.readlyMap.get(index);
        for(Integer i : list){
            if(rowCheck(box,index,i) &&
                    colCheck(box,index,i) &&
                    gridCheck(box,index,i)){
                box[index] = i;
                if(index == this.readlyList.get(this.readlyList.size() - 1)){
                    return true;
                }else {
                    int nextIndex = this.readlyList.get(this.readlyList.indexOf(index) + 1);
                    if(this.getResult(box,nextIndex)){
                        return true;
                    }
                }
            }
        }
        box[index] = 0;
        return false;
    }

    private void getReadly(int[] firstBox){
        Map<Integer,List<Integer>> map = new HashMap<>();
        List<Integer> iList = new ArrayList<>();
        for(int i = 0;i < firstBox.length;i++){
            if(firstBox[i] == 0){
                List<Integer> list = new ArrayList<>();
                for(int k = 1;k < 10;k++){
                    if(rowCheck(firstBox,i,k) &&
                            colCheck(firstBox,i,k) &&
                            gridCheck(firstBox,i,k)){
                        list.add(k);
                    }
                }
                if(list.size() == 1){
                    firstBox[i] = list.get(0);
                }else {
                    map.put(i,list);
                    iList.add(i);
                }
            }
        }
        this.readlyList = iList;
        this.readlyMap = map;
    }

    private boolean rowCheck(int[] box,int index,int init){
        int row = index/9;
        for(int i = 0;i < 9;i++){
            if(box[row * 9 + i] == init){
                return false;
            }
        }
        return true;
    }

    private boolean colCheck(int[] box,int index,int init){
        int col = index%9;
        for(int i = 0;i < 9;i++){
            if(box[i * 9 + col] == init){
                return false;
            }
        }
        return true;
    }

    private boolean gridCheck(int[] box,int index,int init){
        int row = index/9 - (index/9%3);
        int col = index%9 - (index%9%3);
        for(int i = 0;i < 3;i++){
            for(int k= 0;k < 3;k++){
                if(box[(row + i)*9 + col + k] == init){
                    return false;
                }
            }
        }
        return true;
    }

}
