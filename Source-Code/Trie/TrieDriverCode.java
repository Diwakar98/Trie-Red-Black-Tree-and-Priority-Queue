package Trie;

import java.io.*;
import java.net.URL;

public class TrieDriverCode {
    public static void main(String[] args) {
        Trie trie = new Trie();

        File file;
        if (args.length == 0) {
            URL url = TrieDriverCode.class.getResource("INP");
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
        while (true) {
            try {
                if ((st = br.readLine()) == null) break;
                String[] cmd = st.split(" ");
                if (cmd.length == 0) {
                    System.err.println("Error parsing: " + st);
                    return;
                }
                /*trie.printLevel(1);
                trie.printLevel(2);
                trie.printLevel(3);
                trie.printLevel(4);
                trie.printLevel(5);
                trie.printLevel(6);
                trie.printLevel(7);
                trie.printLevel(8);
                trie.printLevel(9);
                trie.printLevel(10);
                trie.printLevel(11);
                trie.printLevel(12);
                trie.printLevel(13);
                trie.printLevel(14);
                trie.printLevel(15);*/
                switch (cmd[0]) {
                    case "INSERT":
                        String contact = br.readLine();
                        Person person = new Person(contact.split(",")[0].trim(), contact.split(",")[1].trim());
                        System.out.println("Inserting: " + person.getName());
                        trie.insert(person.getName(), person);
                        break;
                    case "SEARCH":
                        String search_term = br.readLine();
                        System.out.println("Searching: "+search_term);
                        Object search = trie.search(search_term);
                        if (search != null) {
                            System.out.println("FOUND");
                            Person p=(Person)((TrieNode) search).getValue();
                           System.out.println("[Name: "+p.getName()+", Phone="+p.getNumber()+"]");
                        } else
                            System.out.println("NOT FOUND");
                        break;
                    case "PRINTLEVEL":
                         trie.printLevel(Integer.parseInt(cmd[1]));
                         break;
                    case "MATCH":
                        System.out.println("Matching: "+cmd[1]);
                        TrieNode trieNode = trie.startsWith(cmd[1]);
                        if (trieNode == null){
                            System.out.println("NOT FOUND");
                       }
                        else {
                            System.out.println("MATCHED:");
                            trie.printTrie(trieNode);
                        }
                        break;
                    case "DELETE":
                        search_term = br.readLine();
                        System.out.println("Deleting: "+search_term );
                        trie.delete(search_term);
                        break;

                    case "PRINT":
                        trie.print();
                        break;
                    default:
                        System.err.println("Unknown command: " + cmd[0]);
                }
            }
            catch (IOException e){
                System.err.println("File Not Found");
            }
        }
    }
}
