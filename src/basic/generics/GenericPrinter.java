package basic.generics;

// 제네릭에 extends를 사용하여 자료형에 제한을 둠
public class GenericPrinter<T extends Material> {
    private T material;

    public void setMaterial(T material) {
        this.material = material;
    }

    public T getMaterial() {
        return material;
    }

    public String toString() {
        return material.toString();
    }

    public void printing() {
        material.doPrinting();
    }
}
