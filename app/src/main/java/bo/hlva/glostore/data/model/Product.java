package bo.hlva.glostore.data.model;

/**
* clas model product 
* @author Higor Leonardo Vargas Arrazola
*/
public class Product {

  private String name;
  private int price;
  private String urlImage;
  private String categorie;

  public Product() {}

  public String getName() {
    return this.name;
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

