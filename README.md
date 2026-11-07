# Payroll Management System

حل بسيط لواجب Java عن `Interfaces` و`Comparable` و`Comparator`.

## تشغيل البرنامج

من داخل مجلد المشروع:

```bash
javac -d out src/*.java
java -cp out PayrollSystem
```

## فكرة الحل

- `Taxable`: تحتوي على الثابت `TAX_RATE = 0.15` ودالة حساب الضريبة.
- `BonusCalculable`: تحتوي على دالة حساب المكافأة.
- `Comparable<Employee>`: الواجهة القياسية في Java، وتستخدم للفرز الطبيعي حسب صافي الراتب.
- `Employee`: فئة مجردة تجمع البيانات المشتركة وتطبق `Comparable<Employee>` وواجهتي الضرائب والمكافأة.
- `FullTimeEmployee`: صافي الراتب = الراتب الأساسي + البدلات، والمكافأة 10% من الراتب الأساسي مضروبة في درجة الأداء.
- `ContractorEmployee`: صافي الراتب = أجر الساعة × عدد الساعات، والمكافأة 500 إذا كانت درجة الأداء أكبر من 4.
- `PayrollSystem`: ينشئ القائمة، يفرزها بطريقتين، ثم يحسب مجموع الضرائب والمكافآت.

## لماذا لا يوجد Downcasting؟

القائمة نوعها `Employee`، وكل العمليات تستدعي دوالًا موجودة في `Employee` أو الواجهات. لذلك لا نحتاج إلى تحويل الموظف يدويًا إلى `FullTimeEmployee` أو `ContractorEmployee`. Java تختار التنفيذ المناسب تلقائيًا، وهذا هو مفهوم polymorphism.

## أسئلة متوقعة

### لماذا Employee abstract؟

لأننا لا نريد إنشاء موظف عام بدون نوع عقد، ولأن طريقة حساب صافي الراتب تختلف بين الموظف الكامل والمتعاقد.

### لماذا استخدمنا `Collections.sort(employees)`؟

لأن `Employee` لديه طريقة `compareTo`، وهذه الطريقة تحدد الترتيب الطبيعي للقائمة.

### لماذا استخدمنا `Comparator` بعد ذلك؟

لأن `Comparable` يحدد ترتيبًا طبيعيًا واحدًا، أما `Comparator` فيسمح بعمل ترتيب آخر، مثل الترتيب حسب تاريخ الانضمام.

## رفع المشروع إلى GitHub

بعد إنشاء مستودع فارغ في GitHub، نفذ الأوامر التالية من داخل مجلد المشروع، مع استبدال الرابط برابط مستودعك:

```bash
git init
git add .
git commit -m "Add payroll management system"
git branch -M main
git remote add origin https://github.com/USERNAME/payroll-management-system.git
git push -u origin main
```

### كيف أصبح الفرز حسب الراتب تنازليًا؟

في `compareTo` قارنا راتب الموظف الآخر براتب الموظف الحالي:

```java
Double.compare(other.calculateNetSalary(), this.calculateNetSalary())
```

### كيف تعمل الضريبة مع النوعين؟

الدالة موجودة في `Employee` وتحسب 15% من `calculateNetSalary()`. وبما أن `calculateNetSalary` مختلفة في كل فئة، يتم استخدام التنفيذ الصحيح تلقائيًا.

### ما معنى `protected baseSalary`؟

يعني أن الفئات الابنة تستطيع استخدامه مباشرة، مثل `FullTimeEmployee`، بينما لا يكون متاحًا لأي فئة عشوائية خارج الوراثة.
