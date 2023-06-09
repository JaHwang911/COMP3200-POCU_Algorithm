package academy.pocu.comp3500.assignment4.app;

import academy.pocu.comp3500.assignment4.Project;
import academy.pocu.comp3500.assignment4.project.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Program {

    public static void main(String[] args) {
        testOfficial();
        testBackEdge();
        testBaro();
        testWiki();
        testFindManMonth();
        testFindMinDuration();
        testFindMaxBonusCount();
        testMura();
        testWhiteBeard();

        System.out.println("No prob assignment 4");
    }

    private static void testOfficial() {
        Task[] tasks = createTasks();

        Project project = new Project(tasks);

        int manMonths1 = project.findTotalManMonths("ms1");
        assert (manMonths1 == 17);

        int manMonths2 = project.findTotalManMonths("ms2");
        assert (manMonths2 == 46);

        int minDuration1 = project.findMinDuration("ms1");
        assert (minDuration1 == 14);

        int minDuration2 = project.findMinDuration("ms2");
        assert (minDuration2 == 32);

        int bonusCount1 = project.findMaxBonusCount("ms1");
        assert (bonusCount1 == 6);

        int bonusCount2 = project.findMaxBonusCount("ms2");
        assert (bonusCount2 == 6);
    }

    private static void testBackEdge() {
        Task task0 = new Task("0", 8);
        Task task1 = new Task("1", 3);
        Task task2 = new Task("2", 8);
        Task task3 = new Task("3", 8);
        Task task4 = new Task("4", 3);
        Task task5 = new Task("5", 8);

        task1.addPredecessor(task0);
        task2.addPredecessor(task1, task4);
        task3.addPredecessor(task0);

        task4.addPredecessor(task3, task1);
        task5.addPredecessor(task2, task4);

        Task[] tasks = new Task[] {
                task4, task1, task2, task5, task3, task0
        };

        Project p = new Project(tasks);
        System.out.println(p.findMaxBonusCount("5"));

        Task[] tasks2 = new Task[] {
                task0, task1, task2, task3, task4, task5
        };

        Project p2 = new Project(tasks2);
        System.out.println(p2.findMaxBonusCount("5"));
    }

    private static void testFindManMonth() {
        {
            Task a = new Task("A", 3);
            Task b = new Task("B", 1);
            Task c = new Task("C", 2);
            Task d = new Task("D", 10);

            b.addPredecessor(a);
            d.addPredecessor(b, c);
            Task[] tasks = new Task[]{ a, b, c, d };
            Project p = new Project(tasks);

            int manMonths = p.findTotalManMonths("D");
            assert (manMonths == 16);
        }
        {
            Task a = new Task("A", 3);
            Task b = new Task("B", 1);
            Task c = new Task("C", 2);
            Task d = new Task("D", 10);
            Task e = new Task("E", 5);
            Task f = new Task("F", 20);

            b.addPredecessor(a);
            d.addPredecessor(b, c, e);

            Task[] tasks = new Task[] { a, b, c, d, e, f };
            Project p = new Project(tasks);

            int manMonths = p.findTotalManMonths("D");
            assert (manMonths == 21);
        }
        {
            Task t0 = new Task("0", 1);
            Task t1 = new Task("1", 5);
            Task t2 = new Task("2", 3);
            Task t3 = new Task("3", 2);
            Task t4 = new Task("4", 4);
            Task t5 = new Task("5", 12);
            Task t6 = new Task("6", 10);
            Task t7 = new Task("7", 4);
            Task t8 = new Task("8", 5);
            Task t9 = new Task("9", 2);
            Task t10 = new Task("10", 2);
            Task t11 = new Task("11", 6);
            Task t12 = new Task("12", 9);
            Task t13 = new Task("13", 7);
            Task t14 = new Task("14", 4);
            Task t15 = new Task("15", 3);
            Task t16 = new Task("16", 6);
            Task t17 = new Task("17", 2);
            Task t18 = new Task("18", 9);
            Task t19 = new Task("19", 12);

            t1.addPredecessor(t0);
            t2.addPredecessor(t1, t3);
            t3.addPredecessor(t4);
            t4.addPredecessor(t5);
            t5.addPredecessor(t2);
            t7.addPredecessor(t6);
            t8.addPredecessor(t7);
            t9.addPredecessor(t13);
            t10.addPredecessor(t9);
            t11.addPredecessor(t10, t15);
            t12.addPredecessor(t11);
            t14.addPredecessor(t13);
            t15.addPredecessor(t14);
            t16.addPredecessor(t15, t18);
            t17.addPredecessor(t16);
            t18.addPredecessor(t17);
            t19.addPredecessor(t1, t8, t12);

            Task[] tasks = new Task[] {
                t0, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19};

            Project p = new Project(tasks);
            int manMonths = p.findTotalManMonths("11");
            assert (manMonths == 24);
        }

        // Test A
        {
            Task t0 = new Task("0", 1);
            Task t1 = new Task("1", 5);
            Task t2 = new Task("2", 3);
            Task t3 = new Task("3", 2);
            Task t4 = new Task("4", 4);
            Task t5 = new Task("5", 12);
            Task t6 = new Task("6", 10);
            Task t7 = new Task("7", 4);
            t7.addPredecessor(t0, t1, t2, t3, t4, t5, t6);
            Task[] tasks = new Task[]{t0, t1, t2, t3, t4, t5, t6, t7};

            Project p = new Project(tasks);
            int manMonths = p.findTotalManMonths("7");
            assert (manMonths == 41);
        }

        // Test C
        {
            Task t1 = new Task("1", 1);
            Task t2 = new Task("2", 2);
            Task t3 = new Task("3", 3);
            Task t4 = new Task("4", 4);
            Task t5 = new Task("5", 5);
            Task t6 = new Task("6", 6);
            Task t7 = new Task("7", 7);
            Task t8 = new Task("8", 8);
            Task t9 = new Task("9", 9);
            Task t10 = new Task("10", 10);
            Task t11 = new Task("11", 11);
            Task t12 = new Task("12", 12);

            t2.addPredecessor(t1);
            t3.addPredecessor(t2, t5);
            t5.addPredecessor(t4);

            t6.addPredecessor(t3, t8);
            t7.addPredecessor(t6);
            t8.addPredecessor(t7);

            t10.addPredecessor(t3, t9);
            t12.addPredecessor(t10, t11);

            Task[] tasks = new Task[] { t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12 };
            Project p = new Project(tasks);
            int manMonths = p.findTotalManMonths("3");
            assert (manMonths == 15);

            manMonths = p.findTotalManMonths("6");
            assert (manMonths == 15);

            manMonths = p.findTotalManMonths("10");
            assert (manMonths == 34);

            manMonths = p.findTotalManMonths("12");
            assert (manMonths == 57);

            manMonths = p.findTotalManMonths("9");
            assert (manMonths == 9);
        }
        {
            Task t1 = new Task("1", 1);
            Task t2 = new Task("2", 2);
            Task t3 = new Task("3", 3);
            Task t4 = new Task("4", 4);
            Task t5 = new Task("5", 5);
            Task t6 = new Task("6", 6);
            Task t7 = new Task("7", 7);
            Task t8 = new Task("8", 8);
            Task t9 = new Task("9", 9);
            Task t10 = new Task("10", 10);
            Task t11 = new Task("11", 11);
            Task t12 = new Task("12", 12);

            t2.addPredecessor(t1);
            t3.addPredecessor(t2, t5);
            t5.addPredecessor(t4);

            t6.addPredecessor(t3, t8);
            t7.addPredecessor(t6);
            t8.addPredecessor(t7);
            t9.addPredecessor(t11);

            t10.addPredecessor(t3, t9);
            t12.addPredecessor(t10, t11);

            Task[] tasks = new Task[] { t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12 };
            Project p = new Project(tasks);
            int manMonths = p.findTotalManMonths("3");
            assert (manMonths == 15);

            manMonths = p.findTotalManMonths("6");
            assert (manMonths == 15);

            manMonths = p.findTotalManMonths("10");
            assert (manMonths == 45);

            manMonths = p.findTotalManMonths("12");
            assert (manMonths == 57);

            manMonths = p.findTotalManMonths("9");
            assert (manMonths == 20);
        }
        {
            Task t1 = new Task("1", 1);
            Task t2 = new Task("2", 2);
            Task t3 = new Task("3", 3);
            Task t4 = new Task("4", 4);
            Task t5 = new Task("5", 5);
            Task t6 = new Task("6", 6);
            Task t7 = new Task("7", 7);
            Task t8 = new Task("8", 8);
            Task t9 = new Task("9", 9);
            Task t10 = new Task("10", 10);
            Task t11 = new Task("11", 11);
            Task t12 = new Task("12", 12);
            Task t13 = new Task("13", 13);
            Task t14 = new Task("14", 14);
            Task t15 = new Task("15", 15);

            t2.addPredecessor(t1);
            t3.addPredecessor(t2, t5);
            t5.addPredecessor(t4);
            t13.addPredecessor(t5, t15);
            t14.addPredecessor(t13);
            t15.addPredecessor(t14);

            t6.addPredecessor(t3, t8);
            t7.addPredecessor(t6);
            t8.addPredecessor(t7);
            t9.addPredecessor(t11);

            t10.addPredecessor(t3, t9);
            t12.addPredecessor(t10, t11);

            Task[] tasks = new Task[] { t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12 };
            Project p = new Project(tasks);
            int manMonths = p.findTotalManMonths("3");
            assert (manMonths == 15);

            manMonths = p.findTotalManMonths("6");
            assert (manMonths == 15);

            manMonths = p.findTotalManMonths("10");
            assert (manMonths == 45);

            manMonths = p.findTotalManMonths("12");
            assert (manMonths == 57);

            manMonths = p.findTotalManMonths("9");
            assert (manMonths == 20);
        }
        {
            Task t1 = new Task("1", 1);
            Task t2 = new Task("2", 2);
            Task t3 = new Task("3", 3);
            Task t4 = new Task("4", 4);
            Task t5 = new Task("5", 5);
            Task t6 = new Task("6", 6);
            Task t7 = new Task("7", 7);
            Task t8 = new Task("8", 8);
            Task t9 = new Task("9", 9);
            Task t10 = new Task("10", 10);
            Task t11 = new Task("11", 11);
            Task t12 = new Task("12", 12);
            Task t13 = new Task("13", 13);

            t2.addPredecessor(t1);
            t10.addPredecessor(t2);
            t3.addPredecessor(t2, t5);
            t4.addPredecessor(t3, t8);
            t5.addPredecessor(t4);

            t7.addPredecessor(t6);
            t8.addPredecessor(t7);
            t9.addPredecessor(t7);

            t12.addPredecessor(t11);
            t13.addPredecessor(t9, t10, t12);

            Task[] tasks = new Task[] { t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13 };
            Project p = new Project(tasks);
            int manMonths = p.findTotalManMonths("13");
            assert (manMonths == 71);
        }
    }

    private static void testFindMinDuration() {
        {
            Task a = new Task("A", 3);
            Task b = new Task("B", 1);
            Task c = new Task("C", 2);
            Task d = new Task("D", 10);

            b.addPredecessor(a);
            d.addPredecessor(b, c);
            Task[] tasks = new Task[]{ a, b, c, d };
            Project p = new Project(tasks);

            int minDuration = p.findMinDuration("D");
            assert (minDuration == 14);
        }
        {
            Task a = new Task("A", 3);
            Task b = new Task("B", 1);
            Task c = new Task("C", 2);
            Task d = new Task("D", 10);
            Task e = new Task("E", 5);
            Task f = new Task("F", 20);

            b.addPredecessor(a);
            d.addPredecessor(b, c, e);

            Task[] tasks = new Task[] { a, b, c, d, e, f };
            Project p = new Project(tasks);

            int minDuration = p.findMinDuration("D");
            assert (minDuration == 15);
        }
    }

    private static void testFindMaxBonusCount() {
        {
            Task a = new Task("A", 2);
            Task b = new Task("B", 1);
            Task c = new Task("C", 3);
            Task d = new Task("D", 5);
            Task e = new Task("E", 7);
            Task f = new Task("F", 2);
            Task g = new Task("G", 11);

            b.addPredecessor(a);
            c.addPredecessor(b);
            d.addPredecessor(c);

            f.addPredecessor(b, e);
            g.addPredecessor(d, f);

            Task[] tasks = new Task[]{
                    a, b, c, d, e, f, g
            };
            Project project = new Project(tasks);

            int bonusCount1 = project.findMaxBonusCount("G");
            assert (bonusCount1 == 3);
        }
        {
            Task a = new Task("A", 5);
            Task[] tasks = new Task[] {a};

            Project p = new Project(tasks);
            assert (p.findMaxBonusCount("A") == 5);
        }
    }

    private static Task[] createTasks() {
        Task a = new Task("A", 3);
        Task b = new Task("B", 5);
        Task c = new Task("C", 3);
        Task d = new Task("D", 2);
        Task e = new Task("E", 1);
        Task f = new Task("F", 2);
        Task g = new Task("G", 6);
        Task h = new Task("H", 8);
        Task i = new Task("I", 2);
        Task j = new Task("J", 4);
        Task k = new Task("K", 2);
        Task l = new Task("L", 8);
        Task m = new Task("M", 7);
        Task n = new Task("N", 1);
        Task o = new Task("O", 1);
        Task p = new Task("P", 6);
        Task ms1 = new Task("ms1", 6);
        Task ms2 = new Task("ms2", 8);

        c.addPredecessor(b);
        d.addPredecessor(a);

        ms1.addPredecessor(a, c);

        e.addPredecessor(c);
        f.addPredecessor(g);
        g.addPredecessor(e);

        i.addPredecessor(h);
        j.addPredecessor(ms1);

        k.addPredecessor(j);
        n.addPredecessor(k);
        m.addPredecessor(n);
        l.addPredecessor(m);

        p.addPredecessor(i, j);
        o.addPredecessor(j);

        ms2.addPredecessor(o, p);

        Task[] tasks = new Task[]{
                a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, ms1, ms2
        };

        return tasks;
    }

    private static void testWiki() {
        {
            Task a = new Task("A", 3);
            Task b = new Task("B", 5);
            Task c = new Task("C", 3);
            Task d = new Task("D", 2);
            Task e = new Task("E", 1);
            Task f = new Task("F", 2);
            Task g = new Task("G", 6);
            Task h = new Task("H", 8);
            Task i = new Task("I", 2);
            Task j = new Task("J", 4);
            Task k = new Task("K", 2);
            Task l = new Task("L", 8);
            Task m = new Task("M", 7);
            Task n = new Task("N", 1);
            Task o = new Task("O", 1);
            Task p = new Task("P", 6);
            Task ms1 = new Task("ms1", 6);
            Task ms2 = new Task("ms2", 4);

            c.addPredecessor(b);
            d.addPredecessor(a);

            ms1.addPredecessor(a, c);

            e.addPredecessor(c);
            f.addPredecessor(g);
            g.addPredecessor(e);

            i.addPredecessor(h);
            j.addPredecessor(ms1);

            k.addPredecessor(j);
            n.addPredecessor(k);
            m.addPredecessor(n);
            l.addPredecessor(m);

            p.addPredecessor(i, j);
            o.addPredecessor(j);

            ms2.addPredecessor(o, p);

            Task[] tasks = new Task[]{
                    a, b, c, d, e, f, g, h, i, j, k, l, m, n, o, p, ms1, ms2
            };

            Project project = new Project(tasks);

            int manMonths1 = project.findTotalManMonths("ms1");
            assert (manMonths1 == 17);

            int manMonths2 = project.findTotalManMonths("ms2");
            assert (manMonths2 == 42);

            int minDuration1 = project.findMinDuration("ms1");
            assert (minDuration1 == 14);

            int minDuration2 = project.findMinDuration("ms2");
            assert (minDuration2 == 28);

            int bonusCount1 = project.findMaxBonusCount("ms1");
            assert (bonusCount1 == 6);

            int bonusCount2 = project.findMaxBonusCount("ms2");
            assert (bonusCount2 == 4);
        }
        {
            Task a = new Task("A", 2);
            Task b = new Task("B", 1);
            Task c = new Task("C", 3);
            Task d = new Task("D", 5);
            Task e = new Task("E", 7);
            Task f = new Task("F", 2);
            Task g = new Task("G", 11);

            b.addPredecessor(a);
            c.addPredecessor(b);
            d.addPredecessor(c);

            f.addPredecessor(b, e);
            g.addPredecessor(d, f);

            Task[] tasks = new Task[]{
                    a, b, c, d, e, f, g
            };
            Project project = new Project(tasks);

            int bonusCount1 = project.findMaxBonusCount("G");
            assert (bonusCount1 == 3);
        }
    }

    private static void testBaro() {
        {  // 0
            Task a = new Task("A", 5);
            Task b = new Task("B", 2);
            Task c = new Task("C", 2);
            Task d = new Task("D", 9);
            Task e = new Task("E", 10);

            c.addPredecessor(a, b);
            d.addPredecessor(b, c);
            e.addPredecessor(c, d);

            Task[] test = new Task[]{a, b, c, d, e};

            Project project = new Project(test);
            assert (project.findMaxBonusCount("E") == 4);

            test = new Task[]{b, a, c, d, e};
            assert (project.findMaxBonusCount("E") == 4);
        }

        {  // 1
            Task a = new Task("A", 5);
            Task b = new Task("B", 2);
            Task c = new Task("C", 2);
            Task d = new Task("D", 9);
            Task e = new Task("E", 10);

            c.addPredecessor(b, a);
            d.addPredecessor(b, c);
            e.addPredecessor(c, d);

            Task[] test = new Task[]{a, b, c, d, e};

            Project project = new Project(test);
            assert (project.findMaxBonusCount("E") == 4);

            test = new Task[]{b, a, c, d, e};
            assert (project.findMaxBonusCount("E") == 4);
        }

        {  // 2
            Task a = new Task("A", 5);
            Task b = new Task("B", 2);
            Task c = new Task("C", 2);
            Task d = new Task("D", 9);
            Task e = new Task("E", 10);

            c.addPredecessor(a, b);
            d.addPredecessor(c, b);
            e.addPredecessor(c, d);

            Task[] test = new Task[]{a, b, c, d, e};

            Project project = new Project(test);
            assert (project.findMaxBonusCount("E") == 4);

            test = new Task[]{b, a, c, d, e};
            assert (project.findMaxBonusCount("E") == 4);
        }

        {  // 3
            Task a = new Task("A", 5);
            Task b = new Task("B", 2);
            Task c = new Task("C", 2);
            Task d = new Task("D", 9);
            Task e = new Task("E", 10);

            c.addPredecessor(b, a);
            d.addPredecessor(c, b);
            e.addPredecessor(c, d);

            Task[] test = new Task[]{a, b, c, d, e};

            Project project = new Project(test);
            assert (project.findMaxBonusCount("E") == 4);

            test = new Task[]{b, a, c, d, e};
            assert (project.findMaxBonusCount("E") == 4);
        }

        {  // 4
            Task a = new Task("A", 5);
            Task b = new Task("B", 2);
            Task c = new Task("C", 2);
            Task d = new Task("D", 9);
            Task e = new Task("E", 10);

            c.addPredecessor(a, b);
            d.addPredecessor(b, c);
            e.addPredecessor(d, c);

            Task[] test = new Task[]{a, b, c, d, e};

            Project project = new Project(test);
            assert (project.findMaxBonusCount("E") == 4);

            test = new Task[]{b, a, c, d, e};
            assert (project.findMaxBonusCount("E") == 4);
        }

        {  // 5
            Task a = new Task("A", 5);
            Task b = new Task("B", 2);
            Task c = new Task("C", 2);
            Task d = new Task("D", 9);
            Task e = new Task("E", 10);

            c.addPredecessor(b, a);
            d.addPredecessor(b, c);
            e.addPredecessor(d, c);

            Task[] test = new Task[]{a, b, c, d, e};

            Project project = new Project(test);
            assert (project.findMaxBonusCount("E") == 4);

            test = new Task[]{b, a, c, d, e};
            assert (project.findMaxBonusCount("E") == 4);
        }

        {  // 6
            Task a = new Task("A", 5);
            Task b = new Task("B", 2);
            Task c = new Task("C", 2);
            Task d = new Task("D", 9);
            Task e = new Task("E", 10);

            c.addPredecessor(a, b);
            d.addPredecessor(c, b);
            e.addPredecessor(d, c);

            Task[] test = new Task[]{a, b, c, d, e};

            Project project = new Project(test);
            assert (project.findMaxBonusCount("E") == 4);

            test = new Task[]{b, a, c, d, e};
            assert (project.findMaxBonusCount("E") == 4);
        }

        {  // 7
            Task a = new Task("A", 5);
            Task b = new Task("B", 2);
            Task c = new Task("C", 2);
            Task d = new Task("D", 9);
            Task e = new Task("E", 10);

            c.addPredecessor(b, a);
            d.addPredecessor(c, b);
            e.addPredecessor(d, c);

            Task[] test = new Task[]{a, b, c, d, e};

            Project project = new Project(test);
            assert (project.findMaxBonusCount("E") == 4);

            test = new Task[]{b, a, c, d, e};
            assert (project.findMaxBonusCount("E") == 4);
        }
    }

    private static void testMura() {
        {
            Task a = new Task("A", 5);
            Task b = new Task("B", 2);
            Task c = new Task("C", 2);
            Task d = new Task("D", 9);
            Task e = new Task("E", 10);

            c.addPredecessor(b, a);
            d.addPredecessor(c, b);
            e.addPredecessor(d, c);

            Task[] test = new Task[]{b, a, c, d, e};

            Project project = new Project(test);
            assert (project.findMaxBonusCount("E") == 4);
        }

        {
            Task a = new Task("A", 5);
            Task b = new Task("B", 2);
            Task c = new Task("C", 2);
            Task d = new Task("D", 9);
            Task e = new Task("E", 10);

            c.addPredecessor(a, b);
            d.addPredecessor(b, c);
            e.addPredecessor(c, d);

            ArrayList<Task> tasks = new ArrayList<>(Arrays.asList(a, b, c, d, e));
            ArrayList<Task> perm = new ArrayList<>();
            boolean[] taken = new boolean[5];

            testPerm(0, tasks, perm, taken);
        }
    }

    private static void testWhiteBeard() {
        {
            Task a = new Task("A", 4);
            Task b = new Task("B", 10);
            Task c = new Task("C", 8);
            Task d = new Task("D", 3);
            Task e = new Task("E", 2);
            b.addPredecessor(a);
            c.addPredecessor(b);
            d.addPredecessor(c);
            e.addPredecessor(d);
            Task[] tasks = new Task[]{
                    a, b, c, d, e
            };

            Project project = new Project(tasks);
            int bonusCount1 = project.findMaxBonusCount("E");
            assert (bonusCount1 == 2);
        }

        {
            Task a = new Task("A", 2);
            Task b = new Task("B", 1);
            Task c = new Task("C", 3);
            Task d = new Task("D", 5);
            Task e = new Task("E", 7);
            Task f = new Task("F", 10);
            Task g = new Task("G", 11);

            b.addPredecessor(a);
            c.addPredecessor(b);
            d.addPredecessor(c);

            f.addPredecessor(b, e);
            g.addPredecessor(d, f);

            Task[] tasks = new Task[]{
                    a, b, c, d, e, f, g
            };
            Project project = new Project(tasks);

            int bonusCount1 = project.findMaxBonusCount("G");
            assert (bonusCount1 == 8);

            bonusCount1 = project.findMaxBonusCount("F");
            assert (bonusCount1 == 8);

            bonusCount1 = project.findMaxBonusCount("C");
            assert (bonusCount1 == 1);

            bonusCount1 = project.findMaxBonusCount("A");
            assert (bonusCount1 == 2);
        }

        {
            Task a = new Task("A", 7);
            Task b = new Task("B", 4);
            Task c = new Task("C", 6);
            Task d = new Task("D", 10);
            Task f = new Task("F", 5);
            Task g = new Task("G", 3);
            Task h = new Task("H", 8);

            f.addPredecessor(a, b);
            c.addPredecessor(b);
            g.addPredecessor(c, f);
            h.addPredecessor(a, f);
            d.addPredecessor(g, h);

            Task[] tasks = new Task[]{
                    a, b, c, d, f, g, h
            };
            Project project = new Project(tasks);

            int bonusCount1 = project.findMaxBonusCount("D");
            assert (bonusCount1 == 10);
            bonusCount1 = project.findMaxBonusCount("H");
            assert (bonusCount1 == 8);
            bonusCount1 = project.findMaxBonusCount("G");
            assert (bonusCount1 == 3);
        }
        {
            //케로
            for (int i = 0; i < 100; ++i) {
                Task[] tasks = createTasks(i);

                Project project = new Project(tasks);

                int bonusCount1 = project.findMaxBonusCount("5");

                if (bonusCount1 != 6) {
                    for (Task t : tasks) {
                        System.out.print(t.getTitle() + ", ");
                    }
                    System.out.println();
                }
            }
        }
    }

    private static Task[] createTasks(int seed) {
        Task task0 = new Task("0", 8);
        Task task1 = new Task("1", 3);
        Task task2 = new Task("2", 8);
        Task task3 = new Task("3", 8);
        Task task4 = new Task("4", 3);
        Task task5 = new Task("5", 8);

        task1.addPredecessor(task0);
        task2.addPredecessor(task1, task4);
        task3.addPredecessor(task0);

        task4.addPredecessor(task3, task1);
        task5.addPredecessor(task2, task4);

        Task[] tasks = new Task[]{
                task0, task1, task2, task3, task4, task5
        };

        shuffle(seed, tasks);

        return tasks;
    }

    private static void shuffle(int seed, final Task[] array) {
        Random rand = new Random(seed);

        for (int i = array.length - 1; i > 0; --i) {
            int j = rand.nextInt(i + 1);

            Task temp = array[i];
            array[i] = array[j];
            array[j] = temp;
        }

    }

    private static void testPerm(int j, final ArrayList<Task> tasks, final ArrayList<Task> perm, final boolean[] taken) {
        if (j == 5 && perm.size() != 5) {
            return;
        }

        if (perm.size() == 5) {

            Task[] temp = new Task[5];
            for (int i = 0; i < temp.length; ++i) {
                temp[i] = perm.get(i);
            }

            Project project = new Project(temp);

            int res = project.findMaxBonusCount("E");

            if (res != 4) {
                for (int i = 0; i < perm.size(); ++i) {
                    System.out.print(perm.get(i).getTitle());
                }
            }

            assert res == 4;
        }

        for (int i = 0; i < tasks.size(); ++i) {
            if (!taken[i]) {
                taken[i] = true;
                perm.add(tasks.get(i));
                testPerm(j + 1, tasks, perm, taken);
                perm.remove(perm.size() - 1);
                taken[i] = false;
            }
        }
    }
}
