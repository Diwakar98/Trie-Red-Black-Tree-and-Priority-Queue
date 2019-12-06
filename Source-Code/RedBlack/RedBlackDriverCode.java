package RedBlack;
import Trie.Person;

import java.io.*;
import java.net.URL;

public class RedBlackDriverCode {
    public static void main(String[] args) throws IOException {
        RBTree<String, Person> rbt = new RBTree();
        File file;
        if (args.length == 0) {
            URL url = RedBlackDriverCode.class.getResource("INP");
            file = new File(url.getPath());
        } else {
            file = new File(args[0]);
        }

        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            System.err.println("Input file Not found. "+file.getAbsolutePath());
        }
        String st;
        while ((st = br.readLine()) != null) {
            String[] cmd = st.split(" ");
            if (cmd.length == 0) {
                System.err.println("Error parsing: " + st);
                return;
            }
            //rbt.inorder();
            switch (cmd[0]) {
                case "INSERT":
                    String contact = br.readLine();
                    Person person = new Person(contact.split(",")[0].trim(), contact.split(",")[1].trim());
                    System.out.println("Inserting: " + person.getName());
                    rbt.insert(person.getName(), person);
                    break;
                case "SEARCH":
                    String search_key = br.readLine();
                    System.out.println("Searching for: " + search_key);
                    RedBlackNode search = rbt.search(search_key);
                    if (search != null && search.getValues() != null) {
                         //System.out.println(search.list);
                        for (Object person1 : search.getValues()) {
                             //System.out.println("XX");
                            System.out.println("[Name: "+((Trie.Person)person1).getName()+", Phone="+((Trie.Person)person1).getNumber()+"]");
                        }
                    } else {
                        System.out.println("Not Found");
                    }

                    break;
                default:
                    System.err.println("Unknown command: " + cmd[0]);
            }
        }
        //rbt.inorder();
    }
}
