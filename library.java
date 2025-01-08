import java.io.*;
import java.util.*;
class Library{
    public static void main(String[] args) throws Exception {
        System.out.println("WELCOME TO TALENTELY LIBRARY");
        LibraryManagement lm=new LibraryManagement();
        lm.intlize();
        //lm.intilize();
        Scanner sc=new Scanner(System.in);

        while(true)
        {
            System.out.println("***************************************************************");
            System.out.println("What do you want to do\n1.Add Book\n2.View Books\n3.Search\n4.Issue Book\n5.Return Book\n6.View Issued Book\n7.Delete Book\n8.Save\n9.exit");
            int ch=sc.nextInt();
            switch(ch)
            {
                case 1:
                    System.out.println();
                    lm.addBook();
                    break;
                case 2:
                    System.out.println();
                    lm.viewBooks();
                    break;
                case 3:
                    System.out.println();
                    lm.search();
                    break;
                case 4:
                    System.out.println();
                    lm.issue();
                    break;
                case 5:
                    System.out.println();
                    lm.returnBook();
                    break;
                case 6:
                    System.out.println();
                    lm.viewIssusedBook();
                    break;
                case 7:
                    System.out.println();
                    lm.delete();
                    break;
                case 8:
                    System.out.println();
                    lm.saveData();
                    break;
                case 9:
                    lm.exit();
                    break;
                
            }

            System.out.println();
        }

    }
}

class Book{
    public String title;
    public String author;
    int ISBN;
    Book(String titl,String autho, int ISB)
    {
        title=titl;
        author=autho;
        ISBN=ISB;

    }
    String getTitle()
    {
        return title;
    }
}



class LibraryManagement{
    Scanner sc=new Scanner(System.in);
    HashMap<Book,Integer> map=new HashMap<>(); 
    HashSet<User> users=new HashSet<>(); 
    void intlize() throws Exception
    {
        BufferedReader br=new BufferedReader(new FileReader("data.ind"));
        String tit=br.readLine();
        while(tit!=null){
            
            String aut=br.readLine();
            int is=Integer.parseInt(br.readLine());
            map.put(new Book(tit,aut,is),1);


            tit=br.readLine();
            
        }
        br.close();
    }
    void addBook()
    {
        System.out.println("Enter the book name");
        String name=sc.next();
        System.out.println("Enter the Author name");
        String auth=sc.next();
        System.out.println("Enter the ISBN");
        int Isbn=sc.nextInt();
        Book bk=new Book(name,auth,Isbn);
        map.put(bk,1);
    }
    void viewBooks()
     {
        //System.out.println("----------------------------------------------------------------------------------------------------2");
        int i=1;
        for(Book bk: map.keySet())
        {
            System.out.println(i+")");
            i++;
            
            System.out.println(" Title : "+bk.title);
            System.out.println(" Author: "+bk.author);
            System.out.println(" ISBN: "+bk.ISBN);
            System.out.println();

        }
     }
    void search()
    {
        System.out.println("Enter by which you need to search \n1.title\n2.Author\n3.ISBN");
        int ch=sc.nextInt();
        if(ch==1)
        {
            System.out.println("eNTER THE TITLE");
            String tit=sc.next();
            for(Book bk:map.keySet())
            {
                if(bk.title.equals(tit))
                {
                    
                    System.out.println("Title : "+bk.title);
                    System.out.println("Author: "+bk.author);
                    System.out.println("ISBN: "+bk.ISBN);
                    break;
                }
            }
        }
        else if(ch==2)
        {
            System.out.println("ENTER THE AUTHOR");
            String tit=sc.next();
            for(Book bk:map.keySet())
            {
                if(bk.author.equals(tit))
                {
                    
                    System.out.println("Title : "+bk.title);
                    System.out.println("Author: "+bk.author);
                    System.out.println("ISBN: "+bk.ISBN);
                    break;
                }
            }
        }
        else if(ch==3)
        {
            System.out.println("ENTER THE ISBN");
            int tit=sc.nextInt();
            boolean flag=true;
            for(Book bk: map.keySet())
            {
                if(bk.ISBN==(tit))
                {
                    flag=false;
                    
                    System.out.println("Title : "+bk.title);
                    System.out.println("Author: "+bk.author);
                    System.out.println("ISBN: "+bk.ISBN);
                    break;
                }
            }
            if(flag)
            {
                System.out.println("Book Not found");
            }
        }

     }
    void issue(){
        System.out.println("Enter the detauls of the user");
        System.out.println("Name");
        String name=sc.next();
        System.out.println("ID");
        int id=sc.nextInt();
        System.out.println("Enter the book ID");
        int book_id=sc.nextInt();
        boolean flag=true;
        for(Book bk: map.keySet())
            {
                if(bk.ISBN==(book_id))
                {
                    flag=false;
                    int nob=map.get(bk);
                    map.put(bk,nob-1);
                    
                    
                    User u=new User(name,id,bk);
                    users.add(u);
                    
                    
                    break;

                }
            }
            if(flag)
            {
                System.out.println("Book Not AValiable");
            }
    }
    void returnBook(){
        System.out.println("User Id");
        int id=sc.nextInt();
        boolean flag=true;
        for(User u: users)
        {
            if(u.ID==id)
            {
                flag=false;
                map.put(u.bk,map.get(u.bk)+1);
                users.remove(u);
                break;

            }
            if(flag)
            {
                System.out.println("User Not Found");
            }
        }       
    }
    void viewIssusedBook()
    {
        int i=1;
        if(users.size()==0)
        {
            System.out.println("NO books are issued");
        }
        for(User u:users)
        {
            System.out.println(i+")");
            i++;
            System.out.println(" User Name :"+u.name);
            System.out.println(" User ID :"+u.ID);
            System.out.println(" Book title :"+u.bk.title);
            System.out.println(" Book Author :"+u.bk.author);
            System.out.println(" Book ISBN :"+u.bk.ISBN);
            System.out.println();
        }
    }
    void delete()
    {
        System.out.println("Enter The Book ISBN To delete");
        int id=sc.nextInt();
        for(Book bk: map.keySet())
        {
            if(bk.ISBN==id)
            {
                System.out.println("Book Sucessfully Deleted");
                map.remove(bk);
                break;
            }
        }

    }
    void saveData() throws Exception
    {
        FileWriter fw=new FileWriter("data.ind");
        for(Book b: map.keySet())
        {
            fw.write(b.title+"\n");
            fw.write(b.author+"\n");
            fw.write(b.ISBN+"\n");

        }
        fw.close();

    }
    void exit()
    {
        System.exit(0);
    }

}

class User{
    String name;
    int ID;
    Book bk;
    User(String nam,int id,Book b)
    {
        name=nam;
        ID=id;
        bk=b;

    }

}
