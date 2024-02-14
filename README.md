## Tutorial-2
### Reflection
1. List code quality issues yang sudah diperbaiki
- Mengubah import modul
```java
import org.springframework.web.bind.annotation.*;
```
menjadi
```java
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
```
- Mengubah modifier interface yang sebelumnya public menjadi default
- Mengubah for loop menjadi foreach loop
```java
public Product update(Product product) {
    for (int i = 0; i < productData.size(); i++) {
        if (productData.get(i).getProductId().equals(product.getProductId())){
            Product existingProduct=productData.get(i);
            existingProduct.setProductName(product.getProductName());
            existingProduct.setProductQuantity(product.getProductQuantity());
            return existingProduct;
        }
```
menjadi
```java
public Product update(Product product) {
    for (Product product1: productData) {
        if (product1.getProductId().equals(product.getProductId())) {
            Product existingProduct = product1;
            existingProduct.setProductName(product.getProductName());
            existingProduct.setProductQuantity(product.getProductQuantity());
            return existingProduct;
        }
```

2. menurut saya CI/CD workflows saya telah memenuhi definisi CI/CD. Dengan GitHub Actions saya menggunakan beberapa workflow yaitu ci.yml, pmd.yml, dan scorecard.yml yang akan ditrigger setiap ada push/pull. Dari workflow ini makan kode saya telah mengimplementasikan Continuous Integration. Lalu, saya mengimplementasikan Continuous Deployment dengan PaaS koyeb yang akan melakukan deployment setiap ada push/pull.


## Tutorial-1
### Reflection 1
Dalam pengerjaan fitur edit dan delete, prinsip _clean code_ yang telah saya <br>
aplikasikan adalah: <br>
1. _Meaningful Names_ <br>
Dalam mengerjakan kode edit dan delete, saya memberikan penamaan variabel <br>
yang sesuai dengan konteks variabel tersebut.
2. _Function_ <br>
Saya menggunakan fungsi untuk mengerjakan tiap tugas.
3. _Comment_ <br>
Tidak memberikan _comment_ yang _unnecessary_. Hanya beberapa yang dibutuhkan.
4. _Object and Data Structures_
5. _Error Handling_

prinsip secure coding yang saya implementasikan adalah dengan menggunakan <br>
UUID sebagai productId.

Yang dapat saya perbaiki ke depannya adalah beberapa error handling masih saya <br>
lakukan return null tanpa melakukan handling lebih lanjut.

### Reflection 2
1. Setelah membuat unit test, saya menjadi lebih yakin terhadap kode saya. Jumlah <br>
tes yang diperlukan dalam sebuah unit test menurut saya tergantung terhadap kodenya <br>
itu sendiri. Menurut saya 100% code coverage tidak bisa memastikan kode tidak <br> 
akan memiliki bug.
2. JIka kedua Java class memiliki kode yang mirip maka akan memengaruhi cleanliness. <br>
Pengulangan kode akan terjadi. Bisa diperbaiki dengan memanggil setup procedure dari <br>
test suite sebelumnya.