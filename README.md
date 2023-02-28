1)Основной проблемой решения задания являлось чтение из CSV файла, так как поле столбца могло содержать в себе разделитель или двойные кавычки.
Идея решения была взята из https://mkyong.com/java/how-to-read-and-parse-csv-file-in-java/#single-class-to-read-and-parse-a-csv-file

2)Для быстрого нахождения по префиксу необходимый столбец клался в TreeSet<> и использовался метод subSet(). Временная сложность данного метода: O(log(n)).

3)Для оптимизации работы с CSV файлом использовался класс RandomAccessFile и хранение количества байтов до нужной строки.



Для запуска приложения: 
1)mvn clean package
2)cd target
3)java -Xmx7m -jar airports-search-renue.jar 2
