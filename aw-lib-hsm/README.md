#### HSM Integration Library of ePayFrame 


```
		<dependency>
                        <groupId>af.gov.anar.lib</groupId>
                        <artifactId>anar-lib-hsm</artifactId>
                        <version>${project.version}</version>
		</dependency>

```

#### Features



- Calculate CVV
- Validate CVV
- calculate Offset Using Pin
- calculate Offset Using Pin LMK
- calculate Offset Using Pin Block
- change Pin Offset
- validate Terminal Pin
- validate Interchange Pin
- validate DUKPTPin
- calculate KCV
- generate Key
- generate Export Key
- derive IPEK
- derive Export IPEK
- form Key From Components
- import Key
- export Key
- calculate MAC
- validate MAC
- calculate PVV Using Pin
- calculate PVV Using Pinblock 
- validate DUKPT Pin 
- validate Interchange Pin
- validate Terminal Pin
- change PVV
- encrypt Pin Under LMK
- encrypt Pinblock Under LMK
- decrypt Pin Under LMK
- Translate fromZPKToZPK
- Translate fromTPKToZPK
- Translate fromTPKToBDK
- Translate fromBDKToBDK
- Translate fromBDKToZPK
- Translate fromZPKToLMK
- Translate fromTPKToLMK
- Translate fromLMKToZPK


#### Key Types

```java

	ZMK("000", ""),
	ZPK("001", "0200"),
	TMK("002", ""),
	TPK("002", ""),
	PVK("002", ""),
	TAK("003", ""),
	WWK("006", ""),
	ZAK("006", "0400"),
	BDK1("009", ""),
	ZEK("00A", ""),
	DEK("00B", ""),
	MK_AC("109", ""),
	MK_SMI("209", ""),
	IPEK("302", ""),
	MK_SMC("309", ""),
	TEK("30B", ""),
	CVK("402", ""),
	CSCK("402", ""),
	MK_DAC("409", ""),
	MK_DN("509", ""),
	BDK2("609", ""),
	MK_CVC3("709", ""),
	BDK3("809", "");

```
#### Documentation 

For more information refer to **Wiki** section of this repository. 
