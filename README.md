Öncelikle repomuzda base bir proje olacak (main branchinde ilk push)
Herkes bu repo linkini kullanarak çalışmak istediği directory'de
```bash
git clone <repo linki>
```
komutunu kullanarak hepimiz aynı base projeden başlayacağız.

Herkesin kendi geliştireceği bir özellik olacağı için herkes kendine yeni bir branch açacak.
```bash
git checkout -b <branch'inizin ismi>
```

Diyelim A kişisi feature1 branch'inde çalışıyor ve B kişisi feature2 branch'inde.
A kişisi ilk başta main branch'i ile senkron olup olmadığını görmek zorunda yoksa conflict ortaya çıkıyor. Bunu şu şekilde yapabiliyor:
```bash
git checkout main
git pull origin main
```
Zaten bu komutlardan sonra up to date olduğunuz bilgisini verir ya da main'e eklenmiş bir şey varsa en yeni halini kodunuza taşır.

Sonrasında main'den gelen bu yeni eklentileri kendi branch'inize taşımak için sırasıyla şu komutları girin:
```bash
git checkout feature1 (feature1 A kişisinin branch'i)
git merge main
```
Bu sayede artık main ile güncel olmuş olacaksınız.

A kişisi şimdi eklemek istediği yeni özellikleri main'e taşıyabilir (Çünkü artık main ile güncel olduğumuzu biliyoruz) Bunu şu şekilde yapabilir:
```bash
git add .
git commit -m "<commit ismi>"
git push origin feature1
```

Böylelikle A kişisi kendi çalıştığı feature1 branch'ine main ile güncel kalarak yeni bir özellik ekledi. Şimdi bunu main'e aktarması lazım. Şu şekilde yapabilir:
>>> GitHub web sitesi üzerinden "Pull Requests" bölümüne tıkla.
>>> base main branch olacak, compare branch feature1 olacak. (A kişisi için)
>>> Create pull request butonuna tıkla.
>>> GitHub main ile conflict var mı diye kontrol ettikten sonra (bu durumda conflict vermemesi lazım çünkü main ile up to date olduğumuzu kontrol etmiştik eğer sizden saniyeler önce tekrar main'i güncelleyen biri olursa o zaman main ile up to date olma commandlerini tekrarlayın) merge et butonuna tıkla (eklediğin özellik çalışıyor ise. Bunun için de main ile up to date olunca kodunu çalıştırmayı dene ve çalıştığını gör öyle merge et çalışmıyorsa pull request'i iptal et). Bu sayede A kişisi eklediği özellikleri main branch'ine eklemiş olacak.

B kişisi için ise her şey aynı. A kişisi main'i güncellediği için B kişisi de main ile up to date olmak zorunda. Yukardaki A kişisi için gerekli olan adımlar B kişisi de takip ederse rahatlıkla eklediği yenilikleri main branch'ine feature2 ile aktarabilir.

Özet
1.Main ile güncel kal:
```bash
git checkout main
git pull origin main
git checkout <senin branch'in>
git merge main
```

2.Artık main ile güncelsin şimdi kendi kodlarını kendi branch'ine ekle:
```bash
git add .
git commit -m "<commit ismi>"
git push origin <senin branch'in>
```

3.Branch'ine eklediğin yenilikleri main branch'ine ekle:
>>> GitHub websitesinden pull request yap.
>>> Conflict aldıysan main ile tekrar up to date kontrolü yap.
>>> Conflict almadıysan pull request sonrası merge yap.

Bu kadar.
