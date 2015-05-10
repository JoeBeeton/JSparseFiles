# JSparseFiles
JSparseFiles is a command line tool to generate sparse files. It is a cross platform application that will work on any platform that supports Java 8+ as well as supporting the creation of Sparse Files. 
So Windows/Linux/BSD/Solaris are all supported as long as the file system supports sparse files which is pretty much all modern file systems. So NTFS will work but FAT32 will not. EXT3/4, BTRFS, ZFS will work but not EXT2 etc. 

## Command Line Usage
To generate a sparse file run the following command
java -jar JSparseFiles.jar -path /location/to/the/sparse/file.dat -size 123456789
The above command would generate a completely sparse file named file.dat under the directory /location/to/the/sparse/.

However JSparseFiles can also be used to generate partially sparse files. To do this use the -datasections flag. For example.
java -jar JSparseFiles.jar -path file.dat -size 123456789 -datasections 1024,4096 
The above command would generate a sparse file with a size of 123456789 and between offset 1024 and 4096 ( inclusive ) it would write mostly random data. This will create a partially sparse file.

### Options
The following options are supported from the command line

#### -path
This is the location of the file to be generated this is a required option.
-path c:/location/to/file.dat

#### -size
This is the size of the file to be generated, this is a required option.
-size 1073741824

#### -datasections
This is the start and end offset of the random data to be written to the file between the specified offsets. This is
a optional argument and multiple datasections can be specified.
-datasections 1024,4096

#### -overwrite
This flag if specified means that if a file exists at the specified path it will be overwritten by this utility. If
this flag is not set and the file already exists the command will return an error.
-overwrites




