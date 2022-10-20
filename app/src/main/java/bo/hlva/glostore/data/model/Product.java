package bo.hlva.glostore.data.model;
import androidx.annotation.Nullable;

/**
* clas model product 
* @author Higor Leonardo Vargas Arrazola
*/
public class Product {

  private String name;
  private String description;
  private int price;
  private String urlImage;
  private String categorie;

  public Product() {}

  public String getName() {
    return this.name;
  }
  
  @Nullable
  public String getDescription(){
      return this.description;
  }

  public int getPrice() {
    return this.price;
  }
  
  public String getUrlImage(){
      return this.urlImage;
  }

  public String getCategorie() {
    return this.categorie;
  }

}

