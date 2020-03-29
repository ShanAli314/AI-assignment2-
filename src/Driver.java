import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Rukhsar Ayub
 */
public class Driver {

    static int m, n, t;
    static ArrayList<String> states;
    static ArrayList<String> actions;
    static int[][] transitionTable;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        //System.out.println("Enter m ");
        m = input.nextInt();
        //System.out.println("Enter n");
        n = input.nextInt();
        //System.out.println("Enter t");
        t = input.nextInt();

        System.out.println("m=" + m);
        System.out.println("n=" +n);
        System.out.println("t=" + t);
        input.nextLine();
        //input.nextLine();

        states = new ArrayList<String>();
        for (int i = 0; i < m; i++) {
         //   System.out.println("Enter State "+i);
            states.add(input.nextLine());
        }
        for (int i = 0; i < m; i++) {
            System.out.println("State "+i +" : "+ states.get(i));
           
        }

        //input.nextLine();

        actions = new ArrayList<String>();
        for (int i = 0; i < n; i++) {
            actions.add(input.nextLine());
        }
        
        for (int i = 0; i < n; i++) {
            System.out.println("Action "+i +" : "+ actions.get(i));
           
        }
        

        transitionTable = new int[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                transitionTable[i][j] = input.nextInt();
            }
        }
        for (int i = 0; i < m; i++) {
            
                System.out.println(transitionTable[i][0] +" "+transitionTable[i][1] + " "+transitionTable[i][2]);
            
        }

        input.nextLine();
        //input.nextLine();

        for (int i = 0; i < t; i++) {
            StringTokenizer t1 = new StringTokenizer(input.nextLine(), "\t");
            String start = (String) t1.nextElement();
            String end = (String) t1.nextElement();
            String[] p = new String[2];
            p[0] = start;
            p[1] = end;
            Node result = BFS(p, n);

            Stack<String> stack = new Stack<String>();
            while (result != null) {
                stack.push(result.action);
                result = result.parent;
            }
            stack.pop();
            while(!stack.isEmpty())
            {
                System.out.print(stack.pop() + "->");
            }
            System.out.println("");
        }
    }

    public static Node BFS(String[] problem, int noOfActions) {
        Node n = new Node(problem[0], null, "");
        Queue<Node> frontier = new LinkedList<>();
        frontier.add(n);
        Queue<String> explored = new LinkedList<>();
        while (true) {
            if (frontier.isEmpty()) {
                return null;
            }
            n = frontier.poll();
            if (goalTest(n, problem[1])) {
                return n;
            }
            explored.add(n.state);
            for (int i = 0; i < noOfActions; i++) {
                Node child = new Node(states.get(transitionTable[states.indexOf(n.state)][i]), n, actions.get(i));
                if (!isMemberOfFrontier(frontier, child) && !isMemberOfExploredSet(explored, child.state)) {
                    if (goalTest(child, problem[1])) {
                        return child;
                    } else {
                        frontier.add(child);
                    }
                }
            }
        }
    }

    /*  function BREADTH-FIRST-SEARCH(problem) returns a solution, or failure
//node ←a node with STATE = problem.INITIAL-STATE, PATH-COST = 0
if problem.GOAL-TEST(node.STATE) then return SOLUTION(node)
frontier ←a FIFO queue with node as the only element
explored ←an empty set
loop do
if EMPTY?( frontier) then return failure
node←POP( frontier ) /* chooses the shallowest node in frontier 
add node.STATE to explored
for each action in problem.ACTIONS(node.STATE) do
child ←CHILD-NODE(problem, node, action)
if child .STATE is not in explored or frontier then
if problem.GOAL-TEST(child .STATE) then return SOLUTION(child )
frontier ←INSERT(child , frontier )  */
    public static boolean isMemberOfFrontier(Queue<Node> f, Node n) {
        for (Node i : f) {
            if (i.state == n.state) {
                return true;
            }
        }
        return false;
    }

    public static boolean isMemberOfExploredSet(Queue<String> ex, String state) {
        for (String i : ex) {
            if (i.equals(state)) {
                return true;
            }
        }
        return false;
    }

    public static boolean goalTest(Node n, String goal) {
        return n.state.equals(goal);
    }
}
