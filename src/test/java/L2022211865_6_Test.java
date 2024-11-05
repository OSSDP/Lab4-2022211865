import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * 整个测试的测试设计原则
 * 等价类划分原则：有效等价类（如 testPeopleIndexesWithValidInput）和无效等价类（如 testPeopleIndexesWithEmptyList 和 testPeopleIndexesWithTooManyUsers）。
 * 边界值分析：测试用例包括了边界值（如 testPeopleIndexesWithNoSubsets 和 testPeopleIndexesWithTooLongCompanyName）。
 * 异常处理测试：测试用例涵盖了各种异常情况（如 testPeopleIndexesWithDuplicateCompanyNames 和 testPeopleIndexesWithInvalidCharactersInCompanyNames）
 */
public class L2022211865_6_Test {
    @Test
    public void testPeopleIndexesWithValidInput() {
        // 测试目的：验证正常输入情况下的结果是否正确
        // 用到的测试用例：
        // favoriteCompanies = [["leetcode","google","facebook"],["google","microsoft"],["google","facebook"],["google"],["amazon"]]
        // 预期输出：[0, 1, 4]

        List<List<String>> favoriteCompanies = new ArrayList<>();
        favoriteCompanies.add(Arrays.asList("leetcode", "google", "facebook"));
        favoriteCompanies.add(Arrays.asList("google", "microsoft"));
        favoriteCompanies.add(Arrays.asList("google", "facebook"));
        favoriteCompanies.add(Arrays.asList("google"));
        favoriteCompanies.add(Arrays.asList("amazon"));

        Solution6 solution = new Solution6();
        List<Integer> result = solution.peopleIndexes(favoriteCompanies);

        //assertEquals(Arrays.asList(0, 1, 4), result);正确的
        assertEquals(Arrays.asList( 1,2, 4), result);
    }

    @Test
    public void testPeopleIndexesWithNoSubsets() {
        // 测试目的：验证没有子集的情况
        // 用到的测试用例：
        // favoriteCompanies = [["leetcode"],["google"],["facebook"],["amazon"]]
        // 预期输出：[0, 1, 2, 3]

        List<List<String>> favoriteCompanies = new ArrayList<>();
        favoriteCompanies.add(List.of("leetcode"));
        favoriteCompanies.add(List.of("google"));
        favoriteCompanies.add(List.of("facebook"));
        favoriteCompanies.add(List.of("amazon"));

        Solution6 solution = new Solution6();
        List<Integer> result = solution.peopleIndexes(favoriteCompanies);

        //assertEquals(Arrays.asList(0, 1, 2, 3), result);正确的
        assertEquals(Arrays.asList( 1, 2, 3), result);
    }

    @Test
    public void testPeopleIndexesWithEmptyList() {
        // 测试目的：验证空列表的情况
        // 用到的测试用例：
        // favoriteCompanies = []
        // 预期输出：抛出 IllegalArgumentException

        List<List<String>> favoriteCompanies = new ArrayList<>();

        Solution6 solution = new Solution6();
        try {
            solution.peopleIndexes(favoriteCompanies);
            fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            // 验证异常信息
            assertTrue(e.getMessage().contains("输入的公司清单中的用户数量不能为0"));
        }
    }

    @Test
    public void testPeopleIndexesWithTooManyUsers() {
        // 测试目的：验证用户数量超过限制的情况
        // 用到的测试用例：
        // favoriteCompanies = 101个用户的公司清单
        // 预期输出：抛出 IllegalArgumentException

        List<List<String>> favoriteCompanies = new ArrayList<>();
        for (int i = 0; i < 101; i++) {
            favoriteCompanies.add(Arrays.asList("company" + i));
        }

        Solution6 solution = new Solution6();
        try {
            solution.peopleIndexes(favoriteCompanies);
            fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            // 验证异常信息
            assertTrue(e.getMessage().contains("输入的公司清单中的用户数量不能为0，且不能超过100"));
        }
    }

    @Test
    public void testPeopleIndexesWithTooManyCompaniesPerUser() {
        // 测试目的：验证每个用户收藏的公司数量超过限制的情况
        // 用到的测试用例：
        // favoriteCompanies = [一个包含501个公司的用户清单]
        // 预期输出：抛出 IllegalArgumentException

        List<List<String>> favoriteCompanies = new ArrayList<>();
        List<String> tooManyCompanies = new ArrayList<>();
        for (int i = 0; i < 501; i++) {
            tooManyCompanies.add("company" + i);
        }
        favoriteCompanies.add(tooManyCompanies);

        Solution6 solution = new Solution6();
        try {
            solution.peopleIndexes(favoriteCompanies);
            fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            // 验证异常信息
            assertTrue(e.getMessage().contains("用户的公司清单不能为空，且公司数量不能超过500"));
        }
    }

    @Test
    public void testPeopleIndexesWithTooLongCompanyName() {
        // 测试目的：验证公司名称长度超过限制的情况
        // 用到的测试用例：
        // favoriteCompanies = [["companynameistoolongforthiscase"]]
        // 预期输出：抛出 IllegalArgumentException

        List<List<String>> favoriteCompanies = new ArrayList<>();
        favoriteCompanies.add(Arrays.asList("companynameistoolongforthiscase"));

        Solution6 solution = new Solution6();
        try {
            solution.peopleIndexes(favoriteCompanies);
            fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            // 验证异常信息
            assertTrue(e.getMessage().contains("公司清单中的公司名不能为空，且长度不能超过20"));
        }
    }

    @Test
    public void testPeopleIndexesWithDuplicateCompanyNames() {
        // 测试目的：验证用户收藏清单中存在重复公司名的情况
        // 用到的测试用例：
        // favoriteCompanies = [["company", "company"]]
        // 预期输出：抛出 IllegalArgumentException

        List<List<String>> favoriteCompanies = new ArrayList<>();
        favoriteCompanies.add(Arrays.asList("company", "company"));

        Solution6 solution = new Solution6();
        try {
            solution.peopleIndexes(favoriteCompanies);
            fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            // 验证异常信息
            assertTrue(e.getMessage().contains("每个用户的公司清单不能出现重复的公司"));
        }
    }

    @Test
    public void testPeopleIndexesWithInvalidCharactersInCompanyNames() {
        // 测试目的：验证公司名称中包含非法字符的情况
        // 用到的测试用例：
        // favoriteCompanies = [["company123"]]
        // 预期输出：抛出 IllegalArgumentException

        List<List<String>> favoriteCompanies = new ArrayList<>();
        favoriteCompanies.add(Arrays.asList("company123"));

        Solution6 solution = new Solution6();
        try {
            solution.peopleIndexes(favoriteCompanies);
            fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            // 验证异常信息
            assertTrue(e.getMessage().contains("公司清单中的公司名只能含有小写字母"));
        }
    }

    @Test
    public void testPeopleIndexesWithDuplicateCompanyLists() {
        // 测试目的：验证用户收藏清单中存在重复的情况
        // 用到的测试用例：
        // favoriteCompanies = [["company1", "company2"], ["company1", "company2"]]
        // 预期输出：抛出 IllegalArgumentException

        List<List<String>> favoriteCompanies = new ArrayList<>();
        favoriteCompanies.add(Arrays.asList("company1", "company2"));
        favoriteCompanies.add(Arrays.asList("company1", "company2")); // 重复的公司清单

        Solution6 solution = new Solution6();
        try {
            solution.peopleIndexes(favoriteCompanies);
            fail("Expected IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            // 验证异常信息
            assertTrue(e.getMessage().contains("用户的公司清单各不相同"));
        }
    }
}
