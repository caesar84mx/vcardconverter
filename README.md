**Vcard Converter**<br>
This is a vcard converter, designed to convert Android contact list to a readable format.<br>
_Note:_ For now, it only converts a contact's full name and its phones list.
<br>
**How to use it**<br>
The application can be run in two modes: windowed and console. <br> 
To run it in windowed mode, you just have to run it with no parameters<br>
To run it in console mode, you should provide it 2 parameters: <br>
`<full_path_to_vcf> <export_format>`,<br>
where `<full_path_to_vcf>` is a full path to a vcard file and `<export_format>` is a format you want to export to.<br>

Build the project with Maven:
`mvn jfx:jar`
to have a jar executable, or:
`mvn jfx:native`
to have an OS native application format.
You can find your built application in `${project.root}/target/jfx` directory.

For now there are two formats available: .txt and .docx. Later, a new functionality may be added.<br>
Anyway, you are free to improve it.<br>