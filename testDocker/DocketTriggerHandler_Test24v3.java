/************************************************************************
Class Name: DocketTriggerHandler_Test
History of changes: 
-------------------------------------------------------------------------
Date                Developer                         Comments   
-------------------------------------------------------------------------
10-31-2017          cristian.pugach@accenture.com     Test Class      
*************************************************************************/

@isTest(seeAllData = false)
private class DocketTriggerHandler_Test {
    
    public static testMethod void DocketCreation_Test(){
                                     
        Test.loadData(MRDRCodeDetail__c.sobjectType, 'TestDataMRDRCodeDetailIndustry');
        Test.loadData(CodeDetail__c.sObjectType, 'TestDataCodeDetailAttribute');        
        List<OM_R1_Attribute_Cloned__c> attrRefListToInsert = Test.loadData(OM_R1_Attribute_Cloned__c.sObjectType, 'TestDataOMR1Attribute');
        
        Map<String, OM_R1_Attribute_Cloned__c> attributeMap = new Map<String, OM_R1_Attribute_Cloned__c>();
        for (OM_R1_Attribute_Cloned__c att : attrRefListToInsert) {
            attributeMap.put(att.AttributeTypeCd__c, att);
        }        
        
        OM_R1_Attribute_Cloned__c asw = attributeMap.get(UTIL_Constants.ACCENTURE_SOFTWARE_CD);
        OM_R1_Attribute_Cloned__c mpi = attributeMap.get(UTIL_Constants.MICROSOFT_PLATFORM_INFORMATION_CD);
        OM_R1_Attribute_Cloned__c digital = attributeMap.get(UTIL_Constants.ACCENTURE_DIGITAL_CD);
        OM_R1_Attribute_Cloned__c iss = attributeMap.get(UTIL_Constants.INDUSTRY_SUB_SEGMENT_CD);
        OM_R1_Attribute_Cloned__c strat = attributeMap.get(UTIL_Constants.STRATEGY_OFFERINGS_CD);
        OM_R1_Attribute_Cloned__c bs = attributeMap.get(UTIL_Constants.BUSINESS_SERVICE_CD);
        
        Docket__c testDoc1 = new Docket__c(Action__c=UTIL_Constants.ACTION_CREATE, Attribute_Type__c=digital.AttributeTypeCd__c, New_Description__c='DigTestInd1', Level_1_New_Description__c='Level 1 Test', Status__c='In Development');
        Docket__c testDoc2 = new Docket__c(Action__c=UTIL_Constants.ACTION_CREATE, Attribute_Type__c=mpi.AttributeTypeCd__c, New_Description__c='MPItest', Status__c='In Development');
        Docket__c testDoc3 = new Docket__c(Action__c=UTIL_Constants.ACTION_CREATE, Level_1_New_Description__c='Level 1 Test', Level_2_New_Description__c='Level 2 test', Attribute_Type__c=asw.AttributeTypeCd__c, New_Description__c='AccSftTestInd1', Status__c='In Development');
        Docket__c testDoc4 = new Docket__c(Action__c=UTIL_Constants.ACTION_CREATE, Level_1_New_Description__c='Level 1A Test', Level_2_New_Description__c='Level 2B test', Attribute_Type__c=asw.AttributeTypeCd__c, New_Description__c=asw.OM_R1_Description__c, Status__c='In Development');
        Docket__c testDoc5 = new Docket__c(Action__c=UTIL_Constants.ACTION_REMAPPING, Attribute_Type__c=asw.AttributeTypeCd__c, Current_Description__c=asw.OM_R1_Description__c, Level_1_Current_Description_Remap__c=asw.OM_R1_Attribute_Level_1__c, Level_2_Current_Description_Remap__c=asw.OM_R1_Attribute_Level_2__c, Level_1_Current_Description__c=asw.OM_R1_Attribute_Level_1__c, Level_2_Current_Description__c=asw.OM_R1_Attribute_Level_2__c, Status__c='In Development');
        
        Insert testDoc1;
        Insert testDoc2;
        Insert testDoc3;
        Insert testDoc4;
        Insert testDoc5;
        
        testDoc2.Attribute_Type__c = iss.AttributeTypeCd__c;
        testDoc2.New_Description__c = 'issTest';
        testDoc2.Industry__c = 'R4';
        update testDoc2;
        testDoc1.Attribute_Type__c = strat.AttributeTypeCd__c;
        testDoc1.New_Description__c = 'StratTest';
        update testDoc1;
        testDoc3.Attribute_Type__c = bs.AttributeTypeCd__c;     
        update testDoc3;
        testDoc3.Attribute_Type__c = asw.AttributeTypeCd__c;
        testDoc3.Level_1_New_Description__c = null;
        testDoc3.Level_1_Current_Description__c = asw.OM_R1_Attribute_Level_1__c;
        testDoc3.Level_2_New_Description__c = null;
        testDoc3.Level_2_Current_Description__c = asw.OM_R1_Attribute_Level_2__c;       
        update testDoc3;
        
    }
    /* @isTest static void codeAndDescriptionExistance_Test() {

Test.loadData(MRDRCodeDetail__c.sobjectType, 'TestDataMRDRCodeDetailIndustry');
Test.loadData(CodeDetail__c.sObjectType, 'TestDataCodeDetailAttribute');        
List<OM_R1_Attribute_Cloned__c> attrRefListToInsert = Test.loadData(OM_R1_Attribute_Cloned__c.sObjectType, 'TestDataOMR1Attribute');

Map<String, OM_R1_Attribute_Cloned__c> attributeMap = new Map<String, OM_R1_Attribute_Cloned__c>();
for (OM_R1_Attribute_Cloned__c att : attrRefListToInsert) {
attributeMap.put(att.AttributeTypeCd__c, att);
}        

OM_R1_Attribute_Cloned__c asw = attributeMap.get(UTIL_Constants.ACCENTURE_SOFTWARE_CD);
OM_R1_Attribute_Cloned__c mpi = attributeMap.get(UTIL_Constants.MICROSOFT_PLATFORM_INFORMATION_CD);
OM_R1_Attribute_Cloned__c digital = attributeMap.get(UTIL_Constants.ACCENTURE_DIGITAL_CD);

Docket__c testD1 = new Docket__c(Action__c=UTIL_Constants.ACTION_CREATE, Attribute_Type__c=digital.AttributeTypeCd__c, SSG_Code__c='unExistingValue', SSG_Description__c='');
Docket__c testD2 = new Docket__c(Action__c=UTIL_Constants.ACTION_CREATE, Attribute_Type__c=digital.AttributeTypeCd__c, SSG_Code__c='', SSG_Description__c='unExistingValue');
Docket__c testD3 = new Docket__c(Action__c=UTIL_Constants.ACTION_CREATE, Attribute_Type__c=digital.AttributeTypeCd__c, SSG_Code__c='', SSG_Description__c='AO - Application Outsourcing');
Docket__c testD4 = new Docket__c(Action__c=UTIL_Constants.ACTION_CREATE, Attribute_Type__c=digital.AttributeTypeCd__c, SSG_Code__c='SUBSG116', SSG_Description__c='');
Docket__c testD5 = new Docket__c(Action__c=UTIL_Constants.ACTION_CREATE, Attribute_Type__c=digital.AttributeTypeCd__c, SSG_Code__c='SUBSG022', SSG_Description__c='AO - Application Outsourcing');
Docket__c testD6 = new Docket__c(Action__c=UTIL_Constants.ACTION_CREATE, Attribute_Type__c=digital.AttributeTypeCd__c, SSG_Code__c='unExistingValue', SSG_Description__c='unExistingValue');
Docket__c testD7 = new Docket__c(Action__c=UTIL_Constants.ACTION_CREATE, Attribute_Type__c=digital.AttributeTypeCd__c, SSG_Code__c='', SSG_Description__c='');

test.startTest();
Insert testD1;
Insert testD2;
Insert testD3;
Insert testD4;
Insert testD5;
Insert testD6;
Insert testD7;

testD1.Attribute_Type__c = asw.AttributeTypeCd__c;
testD1.SSG_Code__c = 'unExistingValue';
testD1.SSG_Description__c='';
update testD1;

testD2.Attribute_Type__c = mpi.AttributeTypeCd__c;
testD2.SSG_Code__c = '';
testD2.SSG_Description__c='unExistingValue';
update testD2;

testD3.Attribute_Type__c = asw.AttributeTypeCd__c;
testD3.SSG_Code__c = '';
testD3.SSG_Description__c='AO - Application Outsourcing';
update testD3;

testD4.Attribute_Type__c = asw.AttributeTypeCd__c;
testD4.SSG_Code__c = 'unExistingValue';
testD4.SSG_Description__c='unExistingValue';
update testD4;

testD5.Attribute_Type__c = asw.AttributeTypeCd__c;
testD5.SSG_Code__c = 'SUBSG116';
testD5.SSG_Description__c='';
update testD5;

testD6.Attribute_Type__c = asw.AttributeTypeCd__c;
testD6.SSG_Code__c = 'SUBSG022';
testD6.SSG_Description__c='AO - Application Outsourcing';
update testD6;

testD7.Attribute_Type__c = asw.AttributeTypeCd__c;
testD7.SSG_Code__c = '';
testD7.SSG_Description__c='';
update testD7;
test.stopTest();    
}*/
    
 static PricebookEntry priceBookEntrySetup(){
              system.debug('inicio setup');
              /*metodo que cree registros en PricebookEntry*/       
              /*CREAR PRODUCTO*/
              Pricebook2 pb2 = new Pricebook2(Name='DIE');
              insert pb2;
              //system.debug('pb2'+ trigger.new);
              //system.debug('pricebook2'+ trigger.newMap);
              system.debug('pb2 id'+ pb2.id);
              system.debug('pb2'+ pb2);

              Product2 gpProduct = new Product2();

              gpProduct.Name = 'BPO - Finance and Accounting';
              gpProduct.ProductCode = 'SUBSG022';
              gpProduct.ProductTypeCd__c = 'GP';
              gpProduct.IsActive = true;
              insert gpProduct;
              system.debug('gpProduct id'+ gpProduct.id);
              system.debug('gpProduct'+ gpProduct);

              Product2 gpProduct2 = new Product2();
              gpProduct2.Name = 'SI - System Integration';
              gpProduct2.ProductCode = 'SUBSG114';
              gpProduct2.ProductTypeCd__c = 'GP';
              gpProduct2.IsActive = true;
              insert gpProduct2;
              system.debug('gpProduct2 id'+ gpProduct2.id);
              system.debug('gpProduct2'+ gpProduct2);


              /*Crear pricebook entry*/
              PricebookEntry entry = new PricebookEntry();
              entry.Pricebook2Id = Test.getStandardPricebookId();
              entry.Product2Id = gpProduct.id;
              entry.UnitPrice = 10;       
              entry.IsActive = true;
              entry.UseStandardPrice = false;
              entry.IsActive = true;
              entry.UseStandardPrice = false;
              insert entry;
              
              /*Crear pricebook entry*/
              PricebookEntry entry2 = new PricebookEntry();
              entry2.Pricebook2Id = Test.getStandardPricebookId();
              entry2.Product2Id = gpProduct2.id;
              entry2.UnitPrice = 10;       
              entry2.IsActive = true;
              entry2.UseStandardPrice = false;
              entry2.IsActive = true;
              entry2.UseStandardPrice = false;
              system.debug('entry2 id'+ entry.id);
              system.debug('entry2'+ entry);
              return entry2;
}

    
    
@isTest public static void test_docketInsert(){
	
	PricebookEntry pricebookitem = priceBookEntrySetup(); 
	//TODO: mejorar la instanciacion de setup y buscar un mejor nombre
	Test.loadData(MRDRCodeDetail__c.sobjectType, 'TestDataMRDRCodeDetailIndustry');
	Test.loadData(CodeDetail__c.sObjectType, 'TestDataCodeDetailAttribute');
    
	test.startTest();	
	/***************IINSERT EXITO**************/ 
	
	/***************DESCOMENTAR PARA LAS PRUEBAS COMPLETAS*************************/
	/*Docket__c testD1 = new Docket__c(Action__c=UTIL_Constants.ACTION_CREATE,
							Attribute_Type__c=UTIL_Constants.TGP_OFFERINGS_CD,
							Level_1_New_Description__c=UTIL_Constants.MERGE_ACQUISITIONS_CD,
							Level_2_New_Description__c=UTIL_Constants.MERGE_ACQUISITIONS_CD,
							SSG_Code__c ='SUBSG022',
							SSG_Description__c='');
	insert testD1;
	System.debug('New record testD1 id: <' + testD1.id + '> testD1:'+testD1);
	System.AssertEquals(testD1.SSG_Description__c, 'SI - System Integration');  
	
	Docket__c testD2 = new Docket__c(Action__c=UTIL_Constants.ACTION_CREATE, Attribute_Type__c=UTIL_Constants.TGP_OFFERINGS_CD, Level_1_Current_Description__c=UTIL_Constants.ACCENTURE_DIGITAL_CD, Level_1_New_Description__c=UTIL_Constants.ACCENTURE_DIGITAL_CD, SSG_Code__c ='', SSG_Description__c='SI - System Integration');
	insert testD2;           
	System.debug('New record testD2 id: <' + testD2.id + '> testD2:'+testD2);
	System.AssertEquals(testD2.SSG_Code__c , 'SUBSG114');
	
	                 

	
	
	
	                         /***************IINSERT NO EXITO**************/
	Boolean expectedExceptionThrownInsertCodigo = false;
	try
	{
		Docket__c testD3 = new Docket__c(Action__c=UTIL_Constants.ACTION_CREATE, Attribute_Type__c=UTIL_Constants.TGP_OFFERINGS_CD, Level_1_New_Description__c=UTIL_Constants.MERGE_ACQUISITIONS_CD,Level_2_New_Description__c=UTIL_Constants.MERGE_ACQUISITIONS_CD, SSG_Code__c ='SUBSG110_noexist', SSG_Description__c='');
		insert testD3;
                
	}
	catch(Exception e)
	{   
		expectedExceptionThrownInsertCodigo =  e.getMessage().contains(UTIL_Constants.SSG_Code_Ivalid) ? true : false;
		System.AssertEquals(expectedExceptionThrownInsertCodigo, true,'codigo valido- no asignó el codigo de error');
	}
	Boolean expectedExceptionThrownInsertDescription = false;
	try
	{
		Docket__c testD4 = new Docket__c(Action__c=UTIL_Constants.ACTION_CREATE, Attribute_Type__c=UTIL_Constants.TGP_OFFERINGS_CD, Level_1_Current_Description__c=UTIL_Constants.ACCENTURE_DIGITAL_CD, Level_1_New_Description__c=UTIL_Constants.ACCENTURE_DIGITAL_CD, SSG_Code__c ='', SSG_Description__c='Any unexisting description');
		insert testD4;
	}
	catch(Exception e) //capturamos la exepcion, y en e.getMessage  tenemos los errores de la transaccion
	{
		expectedExceptionThrownInsertDescription =  e.getMessage().contains(UTIL_Constants.SSG_Description_Invalid) ? true : false;
		System.AssertEquals(expectedExceptionThrownInsertDescription, true,'codigo valido de desc- no asignó el codigo de error de la desc');
	}   

	
	/***************DESCOMENTAR PARA LAS PRUEBAS COMPLETAS*************************/
	/***************UPDATE EXITO SE VE???**************/
	 testD1.SSG_Code__c ='SUBSG114';
	update testD1;
	System.debug('update record testD1 id: <' + testD1.id + '> con codigo SUBSG022 a:'+testD1.SSG_Code__c);
	System.AssertEquals(testD1.SSG_Code__c, 'SUBSG114');  
	
	testD2.SSG_Description__c =  'BPO - Finance and Accounting';
	update testD2;           
	System.debug('update record testD2 id: <' + testD2.id + '> con desc SI - System Integration a:'+testD2.SSG_Description__c);
	System.AssertEquals(testD2.SSG_Description__c , 'BPO - Finance and Accounting');
	
	/***************UPDATE NO EXITO**************/
	Boolean expectedExceptionThrownUpdateCodigo = false;
	try
    {          
        
        testD1.SSG_Code__c='SUBSG110_noexist';
        update testD1;
        system.debug('codigo actualizado SUBSG110'+ testD1.SSG_Code__c);
        System.AssertEquals(testD1.SSG_Code__c, 'SUBSG110_noexist');
	}
	catch(Exception e) //capturamos la exepcion, y en e.getMessage  tenemos los errores de la transaccion
	{   
		expectedExceptionThrownUpdateCodigo =  e.getMessage().contains(UTIL_Constants.SSG_Code_Ivalid) ? true : false;
		System.AssertEquals(expectedExceptionThrownUpdateCodigo, true,'codigo valido- no asignó el codigo de error');

	}
	Boolean expectedExceptionThrownUpdateDesc = false;	
	try
    { 
        testD2.SSG_Description__c= 'Any unexisting description';                
        update testD2;
        System.AssertEquals(testD2.SSG_Description__c, 'Any unexisting description');
	}
	catch(Exception e) //capturamos la exepcion, y en e.getMessage  tenemos los errores de la transaccion
	{
		expectedExceptionThrownUpdateDesc =  e.getMessage().contains(UTIL_Constants.SSG_Description_Invalid) ? true : false;
		System.AssertEquals(expectedExceptionThrownUpdateDesc, true,'codigo valido de desc- no asignó el codigo de error de la desc');
	}
		
    test.stopTest(); 
    }    
}