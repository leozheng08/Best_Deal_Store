
import java.util.ArrayList;
import java.util.List;

public class Solution {

    int depth = -1;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections){
        List<Integer>[] graph = new ArrayList[n];
        List<List<Integer>> result = new ArrayList<>();
        int[] depthArray = new int[n];
        for(int i=0;i<n;i++){
            depthArray[i] = -1;
        }
        int[] minDepthArray = new int[n];
        for(int i=0;i<n;i++){
            int from = connections.get(i).get(0);
            int to  = connections.get(i).get(1);
            graph[from].add(to);
            graph[to].add(from);
        }
        for(int i=0;i<n;i++){
            dfs(i, depthArray, minDepthArray, graph,result,i);
        }
        return result;
    }

    private void dfs(int i, int[] depthArray, int[] minDepthArray, List<Integer>[] graph, List<List<Integer>> result, int i1) {
        depth++;
        depthArray[i] = depth;
        minDepthArray[i] = depth;
        int parent = i;
        for(int child : graph[i]){
            if(child==i1){
                continue;
            }
            if(depthArray[child]==-1){
                dfs(child, depthArray, minDepthArray,graph,result,parent);
                minDepthArray[i] = Math.min(minDepthArray[i], depthArray[child]);
                if(minDepthArray[i]>depthArray[i1]){
                    List<Integer> ele = new ArrayList<>();
                    ele.add(i1);
                    ele.add(i);
                    result.add(ele);
                }
            }
            else{
                minDepthArray[i] = Math.min(minDepthArray[i], depthArray[child]);
            }
        }

    }

}
