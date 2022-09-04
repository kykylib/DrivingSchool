import java.util.ArrayList;
import java.util.List;

public class DataBase<T> {

   private List<T> groupABC = new ArrayList<>();

   public DataBase() {
      Human human = new Human("Nikita","Liberman",true,true,true);
      Human human1 = new Human("Maks","Voronov",true,false,false);
      Human human2 = new Human("Andry","Karovsky",true,false,false);
      Human human3 = new Human("Nina","Hrooshova",true,false,false);
      Human human4 = new Human("Kiril","Roobkovsky",true,true,true);
      Human human5 = new Human("Vlad","Skubidy",false,true,true);
      Human human6 = new Human("Alex","Govor",false,true,false);
      Human human7 = new Human("Nika","Liberman",false,false,true);
      Human human8 = new Human("Vlad","Rozhchenko",false,false,true);
      Human human9 = new Human("Bogdan","Nazarenko",false,true,false);

      addInfoABC((T) human);
      addInfoABC((T) human1);
      addInfoABC((T) human2);
      addInfoABC((T) human3);
      addInfoABC((T) human4);
      addInfoABC((T) human5);
      addInfoABC((T) human6);
      addInfoABC((T) human7);
      addInfoABC((T) human8);
      addInfoABC((T) human9);
   }

   public List<T> getGroupABC() {
      return groupABC;
   }

   public void addInfoABC(T info){
      groupABC.add(info);
   }

   @Override
   public String toString() {
      return "\n" + groupABC;
   }
}
