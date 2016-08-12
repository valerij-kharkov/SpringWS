package ua.com.csltd.beans.entity;


/**
 * Created by valeriy_solyanik
 * on 08.08.2016.
 */
public class PdfGroupEntity extends AbstractEntityBean {
  private String name;
  private String descr;


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescr() {
    return descr;
  }

  public void setDescr(String descr) {
    this.descr = descr;
  }

  @Override
  public String toString() {
    return "PdfGroupEntity{" +
        "name='" + name + '\'' +
        ", descr='" + descr + '\'' +
        '}';
  }
}
