import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @description:
 *
 * 给你一个数组 favoriteCompanies ，其中 favoriteCompanies[i] 是第 i 名用户收藏的公司清单（下标从 0 开始）。
 *
 * 请找出不是其他任何人收藏的公司清单的子集的收藏清单，并返回该清单下标。下标需要按升序排列。
 *
 *
 *
 * 示例 1：
 *
 * 输入：favoriteCompanies = [["leetcode","google","facebook"],["google","microsoft"],["google","facebook"],["google"],["amazon"]]
 * 输出：[0,1,4]
 * 解释：
 * favoriteCompanies[2]=["google","facebook"] 是 favoriteCompanies[0]=["leetcode","google","facebook"] 的子集。
 * favoriteCompanies[3]=["google"] 是 favoriteCompanies[0]=["leetcode","google","facebook"] 和 favoriteCompanies[1]=["google","microsoft"] 的子集。
 * 其余的收藏清单均不是其他任何人收藏的公司清单的子集，因此，答案为 [0,1,4] 。
 * 示例 2：
 *
 * 输入：favoriteCompanies = [["leetcode","google","facebook"],["leetcode","amazon"],["facebook","google"]]
 * 输出：[0,1]
 * 解释：favoriteCompanies[2]=["facebook","google"] 是 favoriteCompanies[0]=["leetcode","google","facebook"] 的子集，因此，答案为 [0,1] 。
 * 示例 3：
 *
 * 输入：favoriteCompanies = [["leetcode"],["google"],["facebook"],["amazon"]]
 * 输出：[0,1,2,3]
 *
 *
 * 提示：
 *
 * 1 <= favoriteCompanies.length <= 100
 * 1 <= favoriteCompanies[i].length <= 500
 * 1 <= favoriteCompanies[i][j].length <= 20
 * favoriteCompanies[i] 中的所有字符串各不相同 。
 * 用户收藏的公司清单也各不相同 ，也就是说，即便我们按字母顺序排序每个清单， favoriteCompanies[i] != favoriteCompanies[j] 仍然成立。
 * 所有字符串仅包含小写英文字母。
 *
 */
public class Solution6 {
    Set<String>[] s = new Set[100];

    public List<Integer> peopleIndexes(List<List<String>> favoriteCompanies) {
        int n = favoriteCompanies.size();

        //保证1 <= favoriteCompanies.length <= 100
        if (n >100 || favoriteCompanies.isEmpty()){
            throw new IllegalArgumentException("输入的公司清单中的用户数量不能为0，且不能超过100");
        }
        //保证1 <= favoriteCompanies[i].length <= 500
        for (int i = 0; i < n; i++) {
            if (favoriteCompanies.get(i).size() > 500 || favoriteCompanies.get(i).isEmpty()) {
                throw new IllegalArgumentException("用户的公司清单不能为空，且公司数量不能超过500");
            }
        }
        //保证1 <= favoriteCompanies[i][j].length <= 20
        for (int i = 0; i < n; i++) {
            for(String com : favoriteCompanies.get(i)) {
                if (com.length()>20|| com.isEmpty()){
                    throw new IllegalArgumentException("公司清单中的公司名不能为空，且长度不能超过20");
                }
            }
        }
        //保证favoriteCompanies[i] 中的所有字符串各不相同
        for (int i = 0; i < n; i++) {
            HashSet<String> temp = new HashSet<>(favoriteCompanies.get(i));
            if(temp.size()!=favoriteCompanies.get(i).size()){
                throw new IllegalArgumentException("每个用户的公司清单不能出现重复的公司");
            }
        }
        //保证每个用户收藏的清单各不相同
        Set<Set<String>> temp = new HashSet<>();
        for (int i = 0; i < n; i++) {
            Set<String> set =new HashSet<>(favoriteCompanies.get(i));
            if(!temp.add(set)){
                throw new IllegalArgumentException("用户的公司清单各不相同");
            }
        }
        //保证所有字符串仅包含小写英文字母
        for (int i = 0; i < n; i++) {
            for(String com : favoriteCompanies.get(i)) {
                if (!com.matches("^[a-z]+$")){
                    throw new IllegalArgumentException("公司清单中的公司名只能含有小写字母");
                }
            }
        }

        //初始化s
        for (int i = 0; i < 100; ++i) {
            s[i] = new HashSet<String>();
        }

        //存储返回清单的下标
        List<Integer> ans = new ArrayList<Integer>();

        //将favoriteCompanies中的公司清单赋值到s中
        for (int i = 0; i < n; ++i) {
            for (String com : favoriteCompanies.get(i)) {
                s[i].add(com);
            }
        }

        for (int i = 0; i < n; ++i) {
            boolean isSub = false;
            for (int j = 0; j < n; ++j) {
                if (i == j) {
                    continue;
                }
                isSub |= check(favoriteCompanies, i, j);
            }
            if (!isSub) {
                ans.add(i);
            }
        }

        return ans;
    }

    public boolean check (List < List < String >> favoriteCompanies,int x, int y){
        for (String com : favoriteCompanies.get(x)) {
            if (!s[y].contains(com)) {
                return false;
            }
        }
        return true;
    }

    //实验4为步骤7添加一个注释，使得能够PR
}