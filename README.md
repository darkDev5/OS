# OS
Some useful tools to work and get data from your system easily.

## Usage
Here is a sample that gets all the partions information from system.

```java
for (File partition : File.listRoots()) {
      var pt = new Partition(partition.getAbsolutePath());

      System.out.printf("Letter %s: ", pt.getDriveLetter());
      System.out.printf("Display name %s: ", pt.getDisplayName());
      System.out.printf("Is drive %s: ", pt.isDrive());
      System.out.printf("Is floppy %s: ", pt.isFloppy());
      System.out.printf("Is readable %s: ", pt.isReadable());
      System.out.printf("Is writable %s: ", pt.isWritable());
      System.out.printf("Total space %d: ", pt.getTotalSpace());
      System.out.printf("Used space %d: ", pt.getUsedSpace());
      System.out.printf("Total space %d: ", pt.getFreeSpace());
}
```
