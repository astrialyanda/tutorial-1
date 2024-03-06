link web app: https://advshop-aya.koyeb.app/

## Tutorial-4
### Reflection
1. menurut saya flow TDD berguna untuk saya dalam membuat suatu code. Dengan menggunakan flow TDD, saya jadi langsung mengetahui apabila ada kesalahan/bug pada kode saya sehingga bisa segera diperbaiki. Selain itu, karena sudah terlebih dahulu membuat testing, saya jadi mengetahui bagian bagian mana yang mungkin menghasilkan bug sehingga bisa dihindari lebih awal. Hal-hal lain yang harus saya lakukan ke depannya adalah memastikan testing sudah mencakup sebanyak-banyaknya kemungkinan bug.

2. menurut saya F.I.R.S.T. principle yaitu fast, isolated/independent, repeatable, self-validating, dan thorough/timely

## Tutorial-3
### Reflection
1. Explain what principles you apply to your project!
- SRP (Single Responsibility Principle), setiap class hanya memiliki satu tugas atau tanggung jawab. <br>
Aplikasi SRP pada kode saya adalah dengan memisahkan CarController, ProductController, dan HomePageController agar memiliki fungsinya masing-masing.
- OCP (Open for Extension, Closed for Modification), class harus terbuka untuk extend namun tertutup untuk modifikasi. <br>
Aplikasi OCP pada kode saya ada pada peletakan set update di model sehingga update model hanya dapat dilakukan di model itu sendiri.
- ISP (Interface Segregation Principle), membagi interface menjadi kecil. <br>
Aplikasi ISP pada kode saya adalah dengan membagi interface sesuai dengan kebutuhan yaitu CarService untuk Car dan ProductService untuk Product.
- DIP (Dependency Inversion Principle), modul depend kepada abstraction. <br>
Aplikasi DIP pada kode saya dengan mengganti datatype CarServiceImpl menjadi CarService pada controller untuk menghindari coupling.

2. Explain the advantages of applying SOLID principles to your project with examples. <br>
Keuntungan dari penggunaan SOLID principle antara lain
- kode dapat dibaca dengan lebih mudah. Dengan aplikasi SRP, kode dibagi menjadi lebih kecil sesuai fungsinya sehingga kode dapat dimengerti lebih mudah. Keterbacaan kode juga dapat meningkatkan kerja dalam tim karena setiap anggota dapat mengerti kode lebih baik.
- kode dapat diextend. Kode yang mengaplikasikan SOLID principle mendukung extension. Pada kode Car dan Product, jika akan menambahkan atribut baru, dengan mengaplikasikan OCP maka dapat melakukan extension.
- menghindari munculnya lebih banyak bug. Aplikasi OCP meminimalisir munculnya bug baru yang dapat disebabkan oleh memodifikasi kode sebelumnya.
- lebih mudah membuat testing, karena fungsi kode sudah dibagi kecil-kecil.

3. Explain the disadvantages of not applying SOLID principles to your project with examples. <br>
- kurangnya reusability karena fungsi kode tidak dibagi kecil-kecil.
- kode yang lebih sulit dimengerti karena panjang dan sulit dibaca.
- lebih rentan terhadap bug karena melakukan modifikasi pada kode.
- lebih sulit membuat testing karena fungsi kode yang kompleks.

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