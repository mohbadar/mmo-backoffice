### PDF Utility Library of ePayFrame

#### Usage Guide

```
		<dependency>
	               <groupId>af.gov.anar.lib</groupId>
	                <artifactId>anar-lib-pdf</artifactId>
                        <version>${project.version}</version>
		</dependency>

```
#### Features

- Convert HTML obtained from an InputStream to a PDF written to an OutputStream.
- Convert process Template as String to outpustStream
- Take input as template file and convert template to PDF and save to the given path with given fileName.
- Convert InputStream to OutputStream.
- Convert BufferedImage list to Byte Array.
- Merge PDF files.
- Convert data obtained from an InputStream to a password protected PDF written to an OutputStream. If password is null or empty, PDF will not be encrypted.

#### Add following property in your service    `application.properties`
 
```$xslt
anar.lib.pdf.pdf_owner_password=badar1235 
```

If password is null or empty, PDF will not be encrypted.

#### Documentation

Refer to this repository **Wiki** section.
https://github.com/Anar-Framework/anar-lib-pdf/wiki
