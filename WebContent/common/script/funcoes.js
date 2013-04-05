
/***********************************************************************************************************
* nome:       function setColorRadioGroupJSF()
*
* fun��o:     mudar a cor de um radioGroup. Percorre um grupo de radiobuttons, setando atraves da fun��o
*             swapColor o que esta checked para a checkedColor e os demais para a cor padr�o do sistema.
*
* param�tros: Nenhum.   
*
* observa��es: Essa fun��o foi criada para suprir um problema que vem do uso de radiobuttons em JSF.
*			   Ao se adicionar um radiobutton, o jsf renderiza de forma automatica uma table em volta desse radio
*			   impossibilitando assim o correto funcionamento da fun��o setColorRadioGroup. Nessas situa��es a fun��o
*			   setColorRadioGroupJSF dever� ser utilizada.         
************************************************************************************************************/   

function setColorRadioGroupJSF () {
	var objects = document.getElementsByTagName("INPUT");
	for (var i=0; i<objects.length; i++) {
		var thisElement = objects[i];
		if (thisElement.getAttribute("type") == "radio" && thisElement.id != "nCheck")
                {                       
                    swapColorJSF(thisElement);                    
                }
	}
}


/***********************************************************************************************************
* nome:       function setColorRadioGroup()
*
* fun��o:     mudar a cor de um radioGroup. Percorre um grupo de radiobuttons, setando atraves da fun��o
*             swapColor o que esta checked para a checkedColor e os demais para a cor padr�o do sistema.
*
* param�tros: Nenhum.             
************************************************************************************************************/   

function setColorRadioGroup () {
	var objects = document.getElementsByTagName("INPUT");
	for (var i=0; i<objects.length; i++) {
		var thisElement = objects[i];
		if (thisElement.getAttribute("type") == "radio" && thisElement.id != "nCheck")
                {
                    swapColor(thisElement);
                }
	}
}
/***********************************************************************************************************
* nome:       function setColorCheckboxGroup
*
* fun��o:     Tem como fun��o mudar a cor das Checkboxes em estado checked quando a p�gina � carregada.  
*
* param�tros: Nenhum            
************************************************************************************************************/   

function setColorCheckboxGroup () {
	var objects = document.getElementsByTagName("INPUT");
	for (var i=0; i<objects.length; i++) {
		var thisElement = objects[i];
		if (thisElement.getAttribute("type") == "checkbox" && thisElement.id != "nCheck" ) 
                {
                    swapColor(thisElement);
                }
	}
}
/***********************************************************************************************************
* nome:       function on_off_objetos ()
*
* fun��o:     habilitar/desabilitar combos de acordo com a checkbox que estiver selecionada
*
* param�tros: nenhum
************************************************************************************************************/   

function on_off_objetos() {
	var frm = formProcesso;      	
      	if (frm.rdoTipo[0].checked) {  // Autuar Processo      	 
			frm.cmbServidor.disabled = false;
	        frm.cmbUnidade.disabled = true;
		}
       else  {
      	  frm.cmbServidor.disabled = true;  // Associar Processo
         frm.cmbUnidade.disabled = false;
       }
   }
   
/***********************************************************************************************************
* nome:       function set()
*
* fun��o:     Marcar todas as checkboxes de um formulario de nome <form name="checkForm">.
*             Esta fun��o faz internamente uma chamada a fun��o swapColor.
*
* param�tros: Nenhum.
************************************************************************************************************/   

var estado=false;
function set() {
    for (var i=0;i<document.checkForm.length;i++) {
        var object = eval("document.checkForm.checkbox" + i);
		if (object && object!="defined") {
          object.checked = true;
		  swapColor(object);
		}
    }
	estado = true;
}

/***********************************************************************************************************
* nome:       function unset()
*
* fun��o:     Desmarcar todas as checkboxes de um formulario de nome <form name="checkForm">.
*             Esta fun��o faz internamente uma chamada a fun��o swapColor.
*
* param�tros: Nenhum.
************************************************************************************************************/  

function unset() {
    for (var i=0;i<document.checkForm.length;i++) {
        var object = eval("document.checkForm.checkbox" + i);
		if (object && object!="undefined") {
          object.checked = false;
		  swapColor(object);
		}
    }
	estado = false;
}

/***********************************************************************************************************
* nome:       function setUnset()
*
* fun��o:     Alternar a sele��o entre as checkboxes. As que est�o marcadas tornam-se desmarcadas e as 
*             desmarcadas tornam-se marcadas.
*
* param�tros: Nenhum.
************************************************************************************************************/  

function setUnset() {
    if (!estado) {
	  set();
	  estado = true;
	}
	else if (estado) {
	  unset();
	  estado = false
	}
}

/***********************************************************************************************************
* nome:       function accepted()
*
* fun��o:     Usada na consulta CBEX. Esta fun��o marca todas as checkboxes que est�o com o usu�rio.
*
* param�tros: Nenhum.
************************************************************************************************************/  

function accepted() {
    unset();
    for (var i=0;i<document.checkForm.length;i++) {
        var object = eval("document.checkForm.checkbox" + i);
		if (object && object!="undefined") {
		  if (object.parentNode.parentNode.parentNode.className != 'listaProcessosSelecionadoNaoAceito' &&
			  object.parentNode.parentNode.parentNode.className !='listaProcessosTramitado') {
            object.checked = true;
		    swapColor(object);
		  }
		}
    }
	estado = true;
}

/***********************************************************************************************************
* nome:       function notAccepted()
*
* fun��o:     Usada na consulta CBEX. Esta fun��o marca todas as checkboxes que est�o pendentes de aceite.
*
* param�tros: Nenhum.
************************************************************************************************************/  

function notAccepted() {
     unset();
	 var object = eval("document.checkForm.checkbox0");
	 object.checked = true;
     for (var i=1;i<document.checkForm.length;i++) {
        var object = eval("document.checkForm.checkbox" + i);
		if (object && object!="undefined") {
		  if (object.parentNode.parentNode.parentNode.className == 'listaProcessosSelecionadoNaoAceito') {
            object.checked = true;
		    swapColor(object);
		  }
		}
    }
	estado = true;
}


function verificaRecurso (nome,elemento)
{
    var objects = document.getElementsByTagName("INPUT");
    for (var i=0;i<objects.length;i++)
    {
        var thisElement = objects[i];
        if (thisElement.getAttribute("type") == "text")
        {
            if (thisElement.name == nome)
            {  
                if (thisElement.value == "true")
                {
                    var x=window.confirm ("O processo originador possui recurso de reconsidera��o ou pedido de reexame n�o apreciado."+'\n'+"Deseja prosseguir com a autua��o de Cobran�a Executiva?");
                      if (x) {
                        return true;}
                       else {
                      elemento.checked = false;
                      return false;}
                    
                    
            }
            
        }
    }
}
return true;
}

function alertCbexOnClick() {         
    var objects = document.getElementsByTagName("INPUT");     
        var j=0;
        for (var i=0; i<objects.length; i++) {
		var thisElement = objects[i];                                
		if (thisElement.getAttribute("type") == "radio") {
                   
                    if (thisElement.checked)
                    {                        
                    
                        var nome = "consulta:dataTableProcessoOriginador:"+j+":alertCbex";                       
                       return verificaRecurso(nome,thisElement);
                        
                        
                    }    
                    j++;
                }                  
        }
        return true;
}

/**********************************************************************************************************
 * 	Function:	mostraEscondeDiv()
 * 
 * Parametro:
 *  Chk: Objeto checkbox maracado
 *  div: id da div a ser escondida
 **********************************************************************************************************/
function mostraEscondeDiv(Chk, div){
    if(Chk.checked){
		showDiv(div);
	}else{
		hiddenDiv(div);
	}
}

/***********************************************************************************************************
 * Function:     setUnsetTabela()
 *
 * Par�metros:
 *	Checkbox do cabe�alho, ID da tabela
 *
 *   OBS: A fun��o s� foi testada em componentes JSF
 *
 * Exemplo:
 *	<f:form id="form">
 *		<h:dataTable id="tabela1 var="item" value="#{actTabela.elementos}">
 *			<h:column id="todos1">
 *				<f:facet name="header">                   								//Cabecalho da tabela 1
 *					<h:selectBooleanCheckbox value="todosTabela1"						//Checkbox geral 1
 *					onclick="setUnsetTabela(this,'form:tabela1')"/>
 *				</f:facet>
 *					<h:selectBooleanCheckbox value="#{item.selecionado}" id="_0"/>		//Checkbox de cada linha
 *			</h:column>
 *		</h:dataTable>
 *		<h:dataTable id="tabela2 var="item2" value="#{actTabela.elementos2}">
 *			<h:column id="todos2">
 *				<f:facet name="header">                   								//Cabecalho da tabela 2
 *					<h:selectBooleanCheckbox value="todosTabela2"						//Checkbox geral 2
 *					onclick="setUnsetTabela(this,'form:tabela2')"/>
 *				</f:facet>
 *					<h:selectBooleanCheckbox value="#{item.selecionado}" id="_1"/>		//Checkbox de cada linha
 *			</h:column>
 *		</h:dataTable>
 *	</f:form>
 ************************************************************************************************************/
function setUnsetTabela(todos, tabela){
    var objects = document.getElementsByTagName("INPUT");
    for (i = 0; i < objects.length; i++) {
        var thisElement = objects[i];
        if (thisElement.getAttribute("type") == "checkbox") {
            if (thisElement.id.indexOf(tabela) == 0 && thisElement != todos) {
                thisElement.checked = todos.checked;
                swapColor(thisElement);
            }
        }
    }
}

/***********************************************************************************************************
 * Function:     selecionaRadio()
 *
 *	Permite a sele��o de um �nico radio na tela (Inclusive de grupos diferentes) e
 *   desabilita outros componentes passados por parametro e habilita os componentes que n�o foram passados.
 *
 * Autor:
 *	     		Matheus Motta Alves (estagi�rio X01030169101) em 03/03/2008
 *
 * Par�metros:
 *	Radio a ser selecionado e ID dos componentes a serem desabilitados
 *
 * Componentes que podem ser desabilitados:
 *	-Input: (radio,checkbox,text,password)
 *	-ComboBox
 *
 * OBS: Essa fun��o s� foi testada em JSF
 *
 * Exemplo:
 *	<h:selectOneRadio styleClass="envolveRadio" onclick="selecionaRadio(this,'itNome','rbConfirmar');>
 *		<f:selectItem itemLabel="" itemValue="Teste" />
 *	</h:selectOneRadio>
 ************************************************************************************************************/
function selecionaRadio(){
    var args = selecionaRadio.arguments, e, args1, i, j, aux = 1;
    args1 = new Array;
    
    if (args[0] != null) {
        e = args[0];
        
        //Montagem do conjunto de parametros a serem desabilitados
        for (j = 1; j < args.legnth; j++) {
            args1[j - 1] = args[j];
        }
        
        var objects = document.getElementsByTagName("INPUT");
        for (i = 0; i < objects.length; i++) {
            var thisElement = objects[i];
            thisElement.disabled = false;
            if (thisElement.getAttribute("type") == "radio") {
                if (thisElement != e) {
                    thisElement.checked = false;
                    swapColorJSF(thisElement);
                }
                else {
                    thisElement.checked = true;
                    swapColorJSF(e);
                }
            }
            else {
                for (aux = 1; aux < args.length; aux++) {
                    if (thisElement.getAttribute("id") == args[aux]) {
                        thisElement.disabled = true;
                        break;
                    }
                }
            }
        }
        var objects = document.getElementsByTagName("SELECT");
        for (i = 0; i < objects.length; i++) {
            var thisElement = objects[i];
            thisElement.disabled = false;
            for (aux = 1; aux < args.length; aux++) {
                if (thisElement.getAttribute("name") == args[aux]) {
                    thisElement.disabled = true;
                    break;
                }
            }
        }
    }
}

/***********************************************************************************************************
 * Function:     abreJanelaCentralizada1()
 ***********************************************************************************************************/
function abreJanelaCentralizada1(id, url, w, h, resize){
    // window.alert ("abrePopup"+id);
    for (i = 0; i < 5; i++) {
        id = id.toString().replace(":", "");
    }
    //window.alert ("abrePopup"+id);
    w += 32;
    h += 96;
    wleft = (screen.width - w) / 2;
    wtop = (screen.height - h) / 2;
    var win = window.open(url, id, 'width=' + w + ', height=' + h + ', ' +
    'left=' +
    wleft +
    ', top=' +
    wtop +
    ', ' +
    'resizable=' +
    resize +
    ', ' +
    'location=no, menubar=no, ' +
    'status=no, toolbar=no, scrollbars=yes');
    // Just in case width and height are ignored
    win.resizeTo(w, h);
    // Just in case left and top are ignored
    win.moveTo(wleft, wtop);
    win.focus();
}


/***********************************************************************************************************
 * Function:     abreJanelaCentralizada1()
 * 		Aciona um determinado bot�o quando for acionado a tecla enter em um campo texto
 ***********************************************************************************************************/
function trataEnter2(event, idBotao){
    if (window.event) { //Internet Explorer
        tecla = event.keyCode;
    }
    else { //Demais browsers
        tecla = event.which;
    }
    if (tecla == 13) {
        if (window.event) { //IE       
            window.event.returnValue = false;
        }
        else { //Firefox    
            event.returnValue = false;
            event.preventDefault();
            event.stopPropagation();
        }
        document.getElementById(idBotao).click();
    }
}

/***********************************************************************************************************
 *  Function:     recuperaValoresComboSelecionado()
 * 		Recupera os valores de uma combo selecionada e insere em um campo texto
 *
 *  Autor:
 *  		Matheus Motta Alves (x01030169101@tcu.gov.br)
 *
 *  Parametros:
 *  		-Combo: a propria combo ser� passada
 *  		-Cod : id do campo texto que ir� receber o value da combo
 *  		-Nome: id do campo texto que ir� receber o label do combo
 *
 *  Exemplo:
 *  		<h:selectOneMenu onchange="recuperaValoresComboSelecionado(this,'idCampoCodigo','idCampoNome');"/>
 ***********************************************************************************************************/
function recuperaValoresComboSelecionado(Combo, cod, nome){
    Label = new Array(0);
    Valor = new Array(0);
    var idCompleto = Combo.id.substring(0, Combo.id.lastIndexOf(":") + 1);
    var Cod = document.getElementById(idCompleto + cod);
    var Nome = document.getElementById(idCompleto + nome);
    
    
    
    if (Cod != null && Nome != null) {
        // verifica se exite alguem selecionado
        if (Combo.selectedIndex != -1) {
            // salva campos e valores em array, exceto o que foi selecionado
            for (i = 0; i < Combo.length; i++) {
                if (Combo.options[i].selected) {
                    if (Combo(i).value == 0) {
                        Cod.value = '';
                        Nome.value = '';
                    }
                    else {
                        Cod.value = Combo(i).value;
                        Nome.value = Combo(i).text;
                    }
                }
            }
        }
    }
}

/***********************************************************************************************************
 * Function:     selecionaOrigem()
 *
 *	Oculta as divs da aba de origem de acordo com o radio selecioando
 *
 * Autor:
 *	     		Matheus Motta Alves (estagi�rio X01030169101) em 03/03/2008
 *
 * Par�metros:
 *		id dos componentes
 *
 ************************************************************************************************************/
function selecionaAbrir(radio, divBanco, divDiretorio){
    var Radio;
    var objects = document.getElementsByTagName("INPUT");
    for (var i = 0; i < objects.length; i++) {
        var thisElement = objects[i];
        if (thisElement.getAttribute("type") == "radio") {
			if (thisElement.checked) {
				Radio = thisElement;
				break;
			}
        }
    }
	if (Radio != null) {
		if (Radio.value == 'B') {
			showDiv(divBanco)
			hiddenDiv(divDiretorio);
			
		}
		if (Radio.value == 'D') {
			showDiv(divDiretorio);
			hiddenDiv(divBanco);
		}
	}
}


//Group: Formata��o

/***********************************************************************************************************
* Function:       formataQualquerTipoMascara
*      Formata qualquer tipo de m�scara. Substitui todas as fun��es antigas iniciadas por "Formata".
*
* Autor:
*	
*	      Modifica��o para compatibilidade com Firefox e JSF em 02/04/2007 por Farley Cruz dos Santos (estagi�rio SEQUAS).
*
*
* Parametros: 
*			  strField       - campo do formul�rio (objeto)
*			  sMask          - tipo de m�scara
*			  evtKeyPress    - evento
*
* Exemplo:
*	   > onkeypress="return formataQualquerTipoMascara(this, '99.999/9999-9', event);"
*
* Observa��o:
*      Para passar o campo do formul�rio para a fun��o atrav�s do id, utilizar document.getElementById('id_do_campo')
*
*           > onkeypress="return formataQualquerTipoMascara(document.getElementById('campoData'), '99/99/9999', event);"
************************************************************************************************************/

function formataQualquerTipoMascara(strField, sMask, evtKeyPress) 
{
var i, nCount, sValue, fldLen, mskLen,bolMask, sCod, nTecla;

 if(window.event){ //Internet Explorer
      nTecla   = evtKeyPress.keyCode;
 }
 else{ //Demais browsers
      nTecla = evtKeyPress.which;
 } 
sValue = strField.value;

//Verificando se foi pressionado Ctrl+c ou Ctrl+v, permitindo seus efeitos.

//Para permitir que a m�scara seja aplicada sobre o texto colado, � chamada
//a fun��o completaMascara ap�s a execu��o desta fun��o, atrav�s da fun��o
//javascript nativa "setTimeout".
 if((evtKeyPress.ctrlKey && nTecla == 118) || (evtKeyPress.ctrlKey && nTecla == 86)){
     setTimeout("completaMascara(document.getElementById(\'"+strField.id+"\'),\'"+sMask+"\')",100);
     return true;
 }
 
for(i=0; i<sMask.length; i++){
    // Limpa todos os caracteres de formata��o que
    // j� estiverem no campo.
    sValue = sValue.toString().replace( "-", "" );
    sValue = sValue.toString().replace( ".", "" );
    sValue = sValue.toString().replace( "/", "" );
    sValue = sValue.toString().replace( "(", "" );
    sValue = sValue.toString().replace( ")", "" );
    sValue = sValue.toString().replace( " ", "" );
    sValue = sValue.toString().replace( ":", "" );
    sValue = sValue.toString().replace( ",", "" );
    fldLen = sValue.length;
    mskLen = sMask.length;
}

i = 0;
nCount = 0;
sCod = "";
mskLen = fldLen;

while (i <= mskLen) 
{
	bolMask = ((sMask.charAt(i) == "-") || (sMask.charAt(i) == ".") || (sMask.charAt(i) == "/") || (sMask.charAt(i) == ",") )
	bolMask = bolMask || ((sMask.charAt(i) == "(") || (sMask.charAt(i) == ")") || (sMask.charAt(i) == ":") || (sMask.charAt(i) == " "))
      
      if(!((i==mskLen)&&(nTecla==8)))
      {
          if (bolMask) 
          {
            sCod += sMask.charAt(i);
            mskLen++;          }
          else 
          {
            sCod += sValue.charAt(nCount);
            nCount++;
          }
      }
	i++;
}
 strField.value = sCod;

if (nTecla != 8) 
{ // backspace
	if (sMask.charAt(i-1) == "9") 
	{ // apenas n�meros...
	  return ((nTecla > 47) && (nTecla < 58)); } // n�meros de 0 a 9
	else 
	{ // qualquer caracter...
	  return true;
	} 
}
else 
{
	return true;
}
}

/***********************************************************************************************************
* Function:     validaCodigoClasseAssunto()
*
*	Fun��o similar a validaNumero(), por�m aceita somente valores n�mericos incluindo o ponto
*
* Autor:
*	
*	     		Matheus Motta Alves (estagi�rio X01030169101) em 03/03/2008
*
* Par�metros: 
*			    e - Radio selecionado. 
*
* Exemplo:
*
************************************************************************************************************/  
function validaCodigoClasseAssunto(e)
{
	if (document.all) // Internet Explorer
		var tecla = event.keyCode;
	else if(document.layers) // Nestcape
		var tecla = e.which;
		if ((tecla > 47 && tecla < 58) || (tecla == 46) || (tecla == 13 ))// numeros de 0 a 9 ou ponto
			return true;
		else
		{
			if (tecla != 8) // backspace
				event.keyCode = 0;
				//return false;
			else
				return true;
		}
}

/***********************************************************************************************************
* Function:       formataQualquerTipoMascaraDireita
*      Formata qualquer tipo de m�scara, sendo que a m�scara � aplicada da direita para a esquerda. As vantagens
*   desta fun��o em rela��o � formataQualquerTipoMascara s�o: n�o necessidade de preenchimento de zeros � esquerda,
*   datas contendo anos com quatro d�gitos ter�o preenchimento intuitivo, limita��o da digita��o de acordo com a m�scara.
*   Sugest�o: alinhar � direita os campos a serem formatados.
*   Substitui todas as fun��es antigas iniciadas por "Formata".
*
* Autor:
*	
*	      Gustavo de Carvalho Dantas (estagi�rio SESAD)em 09/04/2007
*
*
* Parametros: 
*			  strField       - campo do formul�rio (objeto)
*			  sMask          - tipo de m�scara
*			  evtKeyPress    - evento
*
* Exemplo:
*	   > onkeypress="return formataQualquerTipoMascaraDireita(this, '99.999/9999-9', event);"
*
* Observa��o:
*      Para passar o campo do formul�rio para a fun��o atrav�s do id, utilizar document.getElementById('id_do_campo')
*
*           > onkeypress="return formataQualquerTipoMascaraDireita(document.getElementById('campoData'), '99/99/9999', event);"
************************************************************************************************************/

function formataQualquerTipoMascaraDireita (campo, mask, evt) {        
 vCampo = campo.value;              //vCampo: valor do campo.
 tCampo = vCampo.length;            //tCampo: tamanho do campo.
 tMask = mask.length;               //tMask: tamanho da mascara.    
 vFinal = "";                       //vFinal: return da fun��o.    
 tecla   = evt.keyCode;    //Codigo da tecla digitada pelo usuario
 cont = 0;
 
 //Verificando se foi pressionado Ctrl+c ou Ctrl+v, permitindo seus efeitos.
 
 //Para permitir que a m�scara seja aplicada sobre o texto colado, � chamada
 //a fun��o completaMascara ap�s a execu��o desta fun��o, atrav�s da fun��o
 //javascript nativa "setTimeout".
 if((evt.ctrlKey && tecla == 67) || (evt.ctrlKey && tecla == 86)){
     setTimeout("completaMascara(document.getElementById(\'"+campo.id+"\'),\'"+mask+"\')",100);
 }
 
 // Teclas Insert, Del, Page UP, Page Down, Home, End, setas de movimenta��o, Shift, Ctrl e Tab, Enter
 if (tecla == 8 || tecla == 37 || tecla == 38 || tecla == 39 || 
     tecla == 40 || tecla == 46 || tecla == 36 || tecla == 35 || 
	   tecla == 33 || tecla == 34  || tecla == 45 || tecla == 16 || 
	   tecla == 17 || tecla == 9 || tecla == 13)
 {
	    return; 
 }
 for (i=0; i<tCampo; i++) {
      vCampo = vCampo.replace ("-","");
		vCampo = vCampo.replace (".","");
      vCampo = vCampo.replace ("/","");
      vCampo = vCampo.replace (":","");
      vCampo = vCampo.replace (" ","");
      vCampo = vCampo.replace (",","");
      vCampo = vCampo.replace ("(","");
      vCampo = vCampo.replace (")","");
 }  
  
 for (i=0;i<tMask;i++) {
      if (mask.charAt(i) == "9") {
          cont++;
      }
 }
  
 tCampo = vCampo.length; 
	 if (tCampo >= cont) {
	 	return false;
	 } 
  
 pMask = tMask-1;     
  
 for (i=0;i<tCampo;i++) {
      pMask--;
      while (mask.charAt(pMask) != "9") {
          pMask--;
      }
 }

 cont = 0;
 for (i=pMask;i<tMask;i++) {  
      if (mask.charAt(i) == "9") {            
          vFinal = vFinal + vCampo.charAt(cont);            
          cont++;
      }
      else {
          vFinal = vFinal + mask.charAt(i);             
      }
 }    
 campo.value = "";
 campo.value = vFinal;   
 return true;
}

/***********************************************************************************************************
* Function:       completaMascara
*      Completa, com zeros (0) a esquerda, campos formatados por m�scaras _num�ricas_. Tem como funcionalidade
* garantir que um campo ser� preenchido por completo. Deve ser utilizada em conjunto com uma fun��o de formata��o de m�scara,
* sendo chamada no evento _onblur_ do campo.
*
* Autor:
*	Farley Cruz dos Santos (estagi�rio SEQUAS)em 30/01/2008
*
* Parametros: 
*	strField       - campo (objeto)
*	mascara        - formato da m�scara
*
* Exemplos:
*	   > <input type="text" onkeypress="return formataQualquerTipoMascara(this, '999.999.999-99', event);" onblur="completaMascara(this, '999.999.999-99');">
*      > <input type="text" onkeypress="return formataQualquerTipoMascaraDireita(this, '999.999.999-99', event);" onblur="completaMascara(this, '999.999.999-99');">
*
* Funcionamento:
*       - Recupera o texto do campo;
*       - Remove os caracteres especiais do texto e em seguida aplica a m�scara sobre o texto, completando 
* com zeros a esquerda aonde necess�rio.
************************************************************************************************************/

function completaMascara(campo, mascara){
  novoValor = campo.value;
  if(campo.value.length==0)
      return;
  for (i=0; i<novoValor.length; i++) {
      novoValor = novoValor.replace ("-","");
      novoValor = novoValor.replace (".","");
      novoValor = novoValor.replace ("/","");
      novoValor = novoValor.replace (":","");
      novoValor = novoValor.replace (" ","");
      novoValor = novoValor.replace (",","");
  }
  contValor = novoValor.length-1;
  temp = '';
  //Aplicando a m�scara
  for(i=mascara.length-1;i>=0;i--){
      if(mascara.charAt(i)!='9'){
          temp = mascara.charAt(i)+temp;
      }
      else{
          if(contValor<0){
              temp = '0'+temp;
          }
          else{
              temp = novoValor.charAt(contValor)+temp;
              contValor--;
          }
      }
  }
  novoValor = temp;
  campo.value = novoValor;
}


/***********************************************************************************************************
* Function:       FormataCEP
*      Em DESUSO, usar formataQualquerTipoMascaraDireita(). Formata o CEP no formato 99.999-999.
*
* Autor:
*	
*	      C�ssimo em 07/11/2003
*
*
* Par�metros:
*	 
*			  campo       - nome do tag HTML
*			  teclapres   - variavel(event)
************************************************************************************************************/

function FormataCEP(Campo,teclapres) 
{
	var tecla = teclapres.keyCode;
	vr = Campo.value;
	vr = vr.replace( ".", "" );
	vr = vr.replace( "-", "" );
	tam = vr.length ;

	if ( tam >= 8) { 
		teclapres.keyCode = 0 ;
		return ; 
	}

	if (teclaAltera(teclapres) && !(tecla == 8)) 
	{ // 8 - backspace;
		
		if ( tam == 8) 
		{
			Campo.value = vr.substr(0,2) + '.' + vr.substr(2,3) + '-' + vr.substr(5, tam - 5);
			return ;
		}

		if ( tam > 1 && tam <= 4) 
		{
			Campo.value = vr.substr( 0, 2 ) + '.' + vr.substr(2, tam - 2);
	    }
		 
		if ( tam > 4 && tam <= 7) 
		{
			Campo.value = vr.substr( 0, 2 ) + '.' + vr.substr(2,3) + '-' + vr.substr(5, tam - 5);
		}
	}
}

/***********************************************************************************************************
* Function:       FormataCNPJ
*      Em DESUSO, usar formataQualquerTipoMascaraDireita(). Formata CNPJ.
* Autor:
*	
*	      Rafael em 03/07/2001
*
* Par�metros:
*	 
*			  campo     - nome do tag HTML
*			  teclapres - variavel (event)
************************************************************************************************************/

function FormataCNPJ(Campo,teclapres) {
	var tecla = teclapres.keyCode;
	vr = Campo.value;
	vr = vr.replace( ".", "" );
	vr = vr.replace( ".", "" );
	vr = vr.replace( "/", "" );
	vr = vr.replace( "-", "" );
	tam = vr.length ;

//  	validaNumero(teclapres);

	if (teclaAltera(teclapres) && !(tecla == 8)) { // 8 - backspace;
		if ( tam == 14) {
			Campo.value = vr.substr(0,2) + '.' + vr.substr(2,3) + '.' +
					  vr.substr(5,3) + '/' + vr.substr(8,4) + '-' + vr.substr(12, tam - 12)
			return ;
		 }
		if ( tam > 1 && tam <= 4) {
			Campo.value = vr.substr( 0, 2 ) + '.' + vr.substr(2, tam - 2);
		 }
		if ( tam > 4 && tam <= 7) {
			Campo.value = vr.substr( 0, 2 ) + '.' + vr.substr(2,3) + '.' + vr.substr(5, tam - 5);
		 }
		if ( tam > 7 && tam <= 11) {
			Campo.value = vr.substr(0,2) + '.' + vr.substr(2,3) + '.' + 
					  vr.substr(5,3) + '/' + vr.substr(8, tam - 8);
		 }
		if ( tam > 11 && tam <= 13) {
			Campo.value = vr.substr(0,2) + '.' + vr.substr(2,3) + '.' + 
					  vr.substr(5,3) + '/' + vr.substr(8,4) + '-' + vr.substr(12, tam - 12);
		 }
	}
}

/***********************************************************************************************************
* Function:       FormataCPF
*      Em DESUSO, usar formataQualquerTipoMascaraDireita(). Formata CPF.
*
* Autor:
*	
*	      JKramer em 04/05/2001
*
* Par�metros:
*	 
*			  campo     - nome do tag HTML
*			  teclapres - variavel (event)
************************************************************************************************************/

function FormataCPF(Campo,teclapres) {
	var tecla = teclapres.keyCode;
	vr = Campo.value;
	vr = vr.replace( ".", "" );
	vr = vr.replace( ".", "" );
	vr = vr.replace( ".", "" );
	vr = vr.replace( "/", "" );
	vr = vr.replace( "-", "" );
	tam = vr.length ;

//  	validaNumero(teclapres);

//	if ( tecla != 9 && tecla != 8 ){
	
	if (teclaAltera(teclapres) && !(tecla == 8)) { // 8 - backspace;
		if ( tam == 11) {
			Campo.value = vr.substr(0,3) + '.' + vr.substr(3,3) + '.' +
					  vr.substr(6,3) + '-' + vr.substr(9,2);
			return ;
		 }
		if ( tam > 2 && tam <= 5) {
			Campo.value = vr.substr( 0, 3 ) + '.' + vr.substr(3);
		 }
		if ( tam > 5 && tam <= 8) {
			Campo.value = vr.substr( 0, 3 ) + '.' + vr.substr(3,3) + '.' + vr.substr(6);
		 }
		if ( tam > 8 && tam <=10) {
			Campo.value = vr.substr(0,3) + '.' + vr.substr(3,3) + '.' + 
					  vr.substr(6,3) + '-' + vr.substr(9);
		 }
	}
}

/***********************************************************************************************************
* Function:       FormataData
*		  Em DESUSO, usar formataQualquerTipoMascaraDireita(). Formata um campo como "99/99/9999".
*
* Autor:
*	
*	      -
*
* Par�metros:
*	 
*			  campo     - nome do tag HTML
*			  teclapres - variavel (event)
*
*
* Exemplo:
*	   > <input type="text" ...onKeyDown="validaDigito(event); return FormataValorMonetario(this,15, event); ">
*
* Observa��o:
*        'enter' passa para o proximo campo da tela de origem
************************************************************************************************************/

function FormataData(Campo,teclapres) {
	var tecla = teclapres.keyCode;

      if (tecla == 13)
      {
         return trataEnter(Campo,teclapres);
      }
      else
      {             
         limitaDigitacao(Campo, 10);
         validaNumero(teclapres);


	   if (teclaAltera(teclapres) && !(tecla == 8)) 
         { // 8 - backspace ----  13 - enter;
		vr = Campo.value;
		vr = vr.replace( ".", "" );
		vr = vr.replace( "/", "" );
		vr = vr.replace( "/", "" );
		tam = vr.length;

		if ( tam > 2 && tam < 5 )
			Campo.value = vr.substr( 0, tam - 2  ) + '/' + vr.substr( tam - 2, tam );
		if ( tam >= 5 && tam <= 9 )
			Campo.value = vr.substr( 0, 2 ) + '/' + vr.substr( 2, 2 ) + '/' + vr.substr( 4, 3 ); 	
          }
	}
}

/***********************************************************************************************************
* Function:       FormataMatricula
*      Em DESUSO, usar formataQualquerTipoMascaraDireita(). Formata um campo como "99999999-9".
*
* Autor:
*	
*	      -
*
*
* Par�metros:
*	
*			  campo     - campo em que a fun��o ser� executada
*			  teclapres - tecla pressionada
************************************************************************************************************/

function FormataMatricula(Campo, teclapres) {
	var tecla = teclapres.keyCode;
	vr = Campo.value;
	vr = vr.replace( "-", "" );
	tam = vr.length;

  	validaNumero(teclapres);

	if ( tecla != 9 && tecla != 8 && tecla != 13){
		if (tam >= 1) 
		{
			Campo.value = vr.substr(0, tam) + "-";
		 }
	}
}



//Group: Valida��o


/***********************************************************************************************************
* Function:       validaForm
*       Executa a valida��o de campos de um formul�rio independente do n�mero de campos deste formul�rio. 
*   Para os campos com erro, muda o estilo do campo para 'campoTextoErro', deixando o campo em vermelho, 
*   exibindo uma mensagem de erro e colocando o foco no primeiro campo com erro. Valida��es dispon�veis: 
*   campos obrigat�rios, campos com formato de email. � poss�vel executar mais de uma das valida��es 
*   simultaneamente.
*
* Autor:  
*       Farley Cruz dos Santos (estagi�rio SEQUAS) em 11/04/2007
*	
* Parametros:  
*       O n�mero de par�metros varia de acordo com o n�mero de campos. Devem ser informados, para cada campo, 
*   seguindo a ordem: id do campo a ser validado e  o tipo de valida��o. 
*
*  Tipos de valida��es dispon�veis:
*	R 		- campo obrigat�rio
*	Email 	        - campo de email
*	Cep 		- campo de Cep
*	Cpf 		- campo de Cpf
*       Cnpj 		- campo de Cnpj
*	Data 		- campo de Data
*
* Observa��es:
*       1 - Para o uso com JSF, deve-se concatenar antes da id dos campos a id da form (aonde eles est�o).
*   Como alternativa pode ser utilizado o atributo "forceId" do Tomahawk, eliminando a necessidade de adicionar 
*   a id da form �s ids dos campos. A id da tabela contendo a mensagem de erro deve ser a id do campo 
*   concatenada com 'MsgErro' e a tabela contendo a mensagem gen�rica deve ter obrigatoriamente como id 
*   'msgErroFixa'.
*	2 - As ids dos campos s�o testadas, para evitar a passagem de ids inexistentes, conforme sugest�o do Marco 
*    (Sesad)
*
*      > validaForm('idCampo','tipoValida��o1',...,'idCampoN','tipoValida��oN');
*
* Exemplo:
*      > <input type="submit" class="botoes" onClick="return validaForm('campoNome','R','campoEmail','REmail')"  value="Enviar">
*
* Exemplo JSF:
*      >  <h :commandButton value="Enviar" styleClass="botoes"
*      >        onclick="return validaForm('idForm:campoNome','R','idForm:campoEmail','Email')"
*      >        action="#{BeanValidacao.acaoBotaoOk}"/>
************************************************************************************************************/
function validaForm() { 
var i,primeiro,verfocus=true,test,errors='',args=validaForm.arguments, mensagem;
  if (args[0]==true){
      args = args[1];
  }
  for (i=0; i<(args.length); i+=2) {
	// P�ra o processamento caso a id do campo n�o exista no formul�rio
	if (document.getElementById(args[i]) == null){
		mensagem = "Erro no uso da validaForm!\rCampo com a id "+ args[i] + " n�o foi encontrado."
		alert(mensagem);
		return false;
	}

      test=args[i+1]; 
		campo = args[i]+"MsgErro";
      
      //Verificando a exist�ncia das divs necess�rias
      if (document.getElementById(campo) == null){
		mensagem = "Erro no uso da validaForm!\rDiv contendo msg de erro com a id "+ args[i] + " n�o foi encontrada."
		alert(mensagem);
		return false;
	}
      val=recuperaObjeto(campo);
      var result = true, verifica=true;
      if(test.indexOf('R')!=-1){
          result = result && validaCampoRequerido(document.getElementById(args[i]));
      }
      if((test.indexOf('Email')!=-1)&&verifica){
          result = result && validaEmailPadraoInterfacil(document.getElementById(args[i]));
      }
      else if((test.indexOf('Cep')!=-1)&&verifica){
          result = result && validaCep(document.getElementById(args[i]));
      }
	else if((test.indexOf('Cpf')!=-1)&&verifica){
          result = result && validaCpf(document.getElementById(args[i]));
      }
      else if((test.indexOf('Cnpj')!=-1)&&verifica){
          result = result && validaCnpj(document.getElementById(args[i]));
      }
	else if((test.indexOf('Data')!=-1)&&verifica){
          result = result && validaData(document.getElementById(args[i]));
      }
      
      if(!result){
          document.getElementById(campo).style.display='';
          if(verfocus){
              primeiro=campo;
              verfocus=false;
          }
          errors+="-Erro";
      }
      else{ 
          document.getElementById(campo).style.display='none';
      }
  }	
  var tabelaErro,tabelas = document.getElementsByTagName('table');
  var cont = 0;
  while(cont<tabelas.length){
		if(tabelas[cont].title=='msgErroFixa'){
          tabelaErro=tabelas[cont];
			break;
      }
		cont++;
  }
  if(errors == '')
      tabelaErro.style.display='none';
  else{
      tabelaErro.style.display='';
      
      //Coloca o foco no primeiro campo com erro. Foi retirado por problemas com o Internet Explorer
      //document.getElementById(primeiro).focus();
  }
	if(errors!='')
		window.scrollTo(0,0);
	return (errors == '');
	
}

/***********************************************************************************************************
* Function:       validaFormPorImagem
*	Efetua o mesmo comportamento que a validaForm (faz chamada a ela). Por�m combina a chamada da
* valida��o com o submit do formul�rio. A exist�ncia somente da validaForm n�o era suficiente.
*
* Autores: 
* 	Farley e Larissa em 15/10/2007.
*
* Par�metros:
*	idFormulario (como primeiro argumento) e a lista de campos/verifica��o a serem validados.
*	O n�mero de par�metros varia de acordo com o n�mero de campos. 
*  	Tipos de valida��es dispon�veis: as mesmas da validaForm
*
* Exemplo: 
*	onClick="return validaFormPorImagem('meuFormulario','campoNome','R','campoEmail','REmail')"
*
* Obs:
*	Usar apenas no click da imagem
************************************************************************************************************/
function validaFormPorImagem(){
      var args=validaFormPorImagem.arguments, idFormulario, args1, i;
      args1 = new Array;
	//pega o primeiro elemento	
	if (document.getElementById(args[0]) != null){
		idFormulario = args[0];
	}
      // montagem do novo conjunto de par�metros para a validaForm
      for(i=1; i<args.length; i++){
          args1[i-1] = args[i];
      }
      // par�metro "true" possibilita a marca��o de array como argumento, para tratamento
      // adequado na validaForm
	if(validaForm(true,args1)) {
          document.getElementById(idFormulario).submit();
          }
  }
/***********************************************************************************************************
* Function:       validaCampoRequerido
*      Verifica se o campo � requerido. Colore o campo em vermelho caso o mesmo n�o tenha sido preenchido.
*   Retorna false caso o campo esteja vazio.
*
* Autor:  
*       Farley Cruz dos Santos (estagi�rio SEQUAS) em 11/04/2007
*
* Par�metros:
*       campo - campo que ser� validado
*
* Exemplo: 
*	> onblur="validaCampoRequerido(this);" 
************************************************************************************************************/

function validaCampoRequerido(campo){
  if(campo.tagName=="TABLE"){
		inputs = campo.getElementsByTagName('input');
		for(aux=0;aux<inputs.length;aux++){
			if(inputs[aux].checked){
				campo.className = 'campoTexto';
				return true;
			}
		}
		campo.className = 'campoTextoErro'; 
		return false;
	}
	if(campo.value!=''){ 
      campo.className = 'campoTexto';
      return true;
  }
  else{
      campo.className = 'campoTextoErro'; 
      return false;
  }
}

/***********************************************************************************************************
* Function:       validaEmailPadraoInterfacil
*      Garante que o e-mail seja digitado com a sintaxe correta
*      (char@char.char), id�ntica � fun��o validaEmail. Colore o campo em vermelho caso tenha ocorrido erro de
*      valida��o
*
* Autor:  
*		Equipe do Sesad, modificado por Farley Cruz dos Santos (Estagi�rio SEQUAS) em 11/04/07
*
* Parametros:  
*		campo - campo do formul�rio que ser� validado
*			  
* Exemplo: 
*	> onblur="validaEmailPadraoInterfacil(this);"  
************************************************************************************************************/

function validaEmailPadraoInterfacil(campo){    
  var str = campo.value;
  
  if(str.length==0){
          campo.className = 'campoTexto'; 
          return true;
      }
  if (window.RegExp) {
      var reg1str = "(@.*@)|(\\.\\.)|(@\\.)|(\\.@)|(^\\.)";
      var reg2str = "^.+\\@(\\[?)[a-zA-Z0-9\\-\\.]+\\.([a-zA-Z]{2,3}|[0-9]{1,3})(\\]?)$";
      var reg1 = new RegExp(reg1str);
      var reg2 = new RegExp(reg2str);
      
      if (!reg1.test(str) && reg2.test(str)) {
          campo.className = 'campoTexto';
          return true;
      }
      else {        
          campo.select();
          campo.className = 'campoTextoErro'; 
          return false;        
      }
  }else{
      if(str.indexOf("@") >= 0){
          campo.className = 'campoTexto';
          return true;
      }        
      campo.select();
      campo.className = 'campoTexto';
      return false;
  }
} 

/***********************************************************************************************************
* Function:       validaCep
*      Verifica se o Cep foi informado no formato 99999-999, aceitando somente n�meros.  Colore o campo em vermelho caso 
*      tenha ocorrido erro de valida��o.
*
* Autor:  
*		Farley Cruz dos Santos (Estagi�rio SEQUAS) em 27/04/07
*
* Parametros:  
*		campo - campo do formul�rio que ser� validado
*			  
* Exemplo: 
*	> onblur="validaCep(this);"  
************************************************************************************************************/
function validaCep(campo){
	var valorCampo = campo.value;
	var ExpReg = new RegExp("([0-9]{5})-([0-9]){3}");
	if(valorCampo.length==0){
          campo.className = 'campoTexto'; 
          return true;
      }
	if(valorCampo.search(ExpReg)!=-1){
          campo.className = 'campoTexto'; 
          return true;
	}
	else{
          campo.className = 'campoTextoErro'; 
          return false;
	}
}

/***********************************************************************************************************
* Function:       validaCpf
*      Verifica se o Cpf foi informado no formato 999.999.99-99, aceitando somente n�meros.  Executa a verifica��o do d�gito
*      verificador e colore o campo em vermelho caso tenha ocorrido erro de valida��o.
*
* Autor:  
*		Farley Cruz dos Santos (Estagi�rio SEQUAS) em 27/04/07
*	
* Parametros:  
*		campo - campo do formul�rio que ser� validado
*			  
* Exemplo: 
*	> onblur="validaCpf(this);"  
************************************************************************************************************/
function validaCpf (campo) {
	valorCampo = campo.value;
	str_aux = "";
	erro = false;

	if(valorCampo.length==0){
          campo.className = 'campoTexto';
          return true;
	}
	for (i = 0; i <= valorCampo.length - 1; i++)
		if ((valorCampo.charAt(i)).match(/\d/))
			str_aux += valorCampo.charAt(i);
		else if (!(valorCampo.charAt(i)).match(/[\.\-]/)) {  
			erro = true;
		}
	if (str_aux.length != 11) 
		erro = true;

	soma1 = soma2 = 0;
	for (i = 0; i <= 8; i++) {
		soma1 += str_aux.charAt(i) * (10-i);
		soma2 += str_aux.charAt(i) * (11-i);
	}

	d1 = ((soma1 * 10) % 11) % 10;
	d2 = (((soma2 + (d1 * 2)) * 10) % 11) % 10;

	if ((d1 != str_aux.charAt(9)) || (d2 != str_aux.charAt(10))) 
		erro = true;

	if(erro){
		campo.className = 'campoTextoErro'; 
		return false;
	}
	else{
		campo.className = 'campoTexto'; 
		return true;
	}
}

/***********************************************************************************************************
* Function:       validaCnpj
*      Verifica se o Cnpj foi informado no formato 99.999.999/9999-99, aceitando somente n�meros.  Colore o 
*campo em vermelho caso tenha ocorrido erro de valida��o.
*
* Autor:  
*		Farley Cruz dos Santos (Estagi�rio SEQUAS) em 21/08/07
*	
* Parametros:  
*		campo - campo do formul�rio que ser� validado
*			  
* Exemplo: 
*	> onblur="validaCnpj(this);"  
************************************************************************************************************/

function validaCnpj(campo)
    {
    
    var valorCampo = campo.value;
	
    var ExpReg = new RegExp("([0-9]{2}).([0-9]{3}).([0-9]{3})/([0-9]{4})-([0-9]{2})");
	if(valorCampo.length==0){
          campo.className = 'campoTexto'; 
          return true;
      }
	if(valorCampo.search(ExpReg)!=-1){
          campo.className = 'campoTexto'; 
          return true;
	}
	else{
          campo.className = 'campoTextoErro'; 
          return false;
	}
    }
     

/***********************************************************************************************************
* Function:       validaData
*      Verifica se o data foi informado no formato 99/99/9999. Colore o campo em vermelho caso tenha ocorrido erro de
*      valida��o
*
* Autor:  
*		Alexandre Tavares da Rocha(Estagi�rio SEQUAS) em 30/04/07
*	
*
* Parametros:  
*		campo - campo do formul�rio que ser� validado
*			  
* Exemplo: 
*	> onblur="validaData(this);"  
************************************************************************************************************/
function validaData(campo){
  var date = campo.value;
  var array_data = new Array;
  var ExpReg = new RegExp("(0[1-9]|[12][0-9]|3[01])/(0[1-9]|1[012])/[12][0-9]{3}");
  array_data = date.split("/");
  erro = false;
  ano = parseInt(array_data[2],10);
  if(date.length==0){
      campo.className = 'campoTexto'; 
      return true;
  }
  if((ano<1900)||(ano>2099))
	erro = true;
  if ( date.search(ExpReg) == -1 )
	erro = true;
	//Valido os meses que nao tem 31 dias com execao de fevereiro
  else 
      //Valido os meses com 30 dias.
      if ( ( ( array_data[1] == 4 ) || ( array_data[1] == 6 ) ||
         ( array_data[1] == 9 ) || ( array_data[1] == 11 ) ) && ( array_data[0] > 30 ) )
          erro = true;
      //Valido o mes de fevereiro
      else 
          if ( array_data[1] == 2 ) {
          //Valido ano que nao e bissexto
          if ( ( array_data[0] > 28 ) && ( ( array_data[2] % 4 ) != 0 ) )
          	erro = true;
          //Valido ano bissexto
          if ( ( array_data[0] > 29 ) && ( ( array_data[2] % 4 ) == 0 ) )
          	erro = true;
	}
	if(erro){
		campo.className = 'campoTextoErro'; 
		return false;
	}
	else{
		campo.className = 'campoTexto'; 
		return true;
	}
}

/***********************************************************************************************************
* Function:       validaNumero
*      Verifica se � numerico, permitindo a inclus�o de virgula ou ponto. Diferentemente da fun��o "validaDigito()"
*      n�o trata teclas como HOME, END, BACKSPACE, INSERT, DEL.
*
* Autor:
*	
*	      -
*
* Par�metros:
*	
*			  e - evento
************************************************************************************************************/

function validaNumero(e)
{
	if (document.all) // Internet Explorer
		var tecla = event.keyCode;
	else if(document.layers) // Nestcape
		var tecla = e.which;
		if ((tecla > 47 && tecla < 58) || (tecla == 44) || (tecla == 46))// numeros de 0 a 9 ou virgula ou ponto
			return true;
		else
		{
			if (tecla != 8) // backspace
				event.keyCode = 0;
				//return false;
			else
				return true;
		}
}


/***********************************************************************************************************
* Function:       validaDigito
*      Valida o conte�do de uma caixa de texto permitindo apenas a digita��o de caracteres num�ricos. Subistitui
*	   a fun��o "validaTecla(campo,event)".
*
* Autor:
*	
*	      C�ssimo em 07/11/2003
*
*	      Dr�usio (corre��o) em 13/03/2003
*
* Par�metros:
*	 
*			  e    - evento
*
*
* Exemplo:
*	  >  <input id="txtNumProcesso" type="text" onKeyDown="validaDigito(event);">
*
*
************************************************************************************************************/


function validaDigito(e)
{
 var controle = false;
 
 // Pega o valor ASCII da tecla que o usu�rio pressionou   
 if(window.event){ //Internet Explorer
      tecla   = e.keyCode;
 }
 else{ //Demais browsers
      tecla = e.which;
 }

 // Permite a digita��o das seguintes teclas: Backspace, Insert, Del, Page UP, Page Down, Home, End, setas de movimenta��o e Shift.
 // Acrescentada a tecla Tab em 13/03/2007
 if (tecla == 8 || tecla == 37 || tecla == 38 || tecla == 39 || 
     tecla == 40 || tecla == 46 || tecla == 36 || tecla == 35 || 
	   tecla == 33 || tecla == 34  || tecla == 45 || tecla == 16 || tecla == 9 || tecla==13)
 {
	    return; 
 }

 // Verifica se a tecla � um d�gito, sendo que o shift n�o pode estar sendo pressionado
 if ((tecla >= 48 && tecla <= 57) && !e.shiftKey){
 		return; 	
 }
 
 //  Verifica se a tecla � um d�gito do teclado n�m�rico
 if (tecla >= 96 && tecla <= 105){
 		return; 	
 }
 
 // Permite a digita��o da tecla alt
 if (e.altKey){
		return; 	
 }
    
 // Verifica se foi entrada a sequ�ncia Ctrl+c ou Ctrl+v
 if((e.ctrlKey && tecla == 67) || (e.ctrlKey && tecla == 86)){
     controle = true;
     if(browser.isIE){
        setTimeout("validaCampo(\'"+e.srcElement.id+"\')",100);
     }
     else{
        setTimeout("validaCampo(\'"+e.target.id+"\')",100);
     }
 }   
 var caracter = String.fromCharCode(tecla);
 
 //Verifica se o caractere n�o est� entre 0-9, se foi utilizada a sequ�ncia Ctrl+c ou Ctrl+v e se a tecla Shift estava pressionada
 //Se verdadeiro, n�o permite a digita��o do caractere.
 if(((("0123456789".indexOf(caracter) == -1) && !controle)) || e.shiftKey)
 {        
      if (window.event){ //IE       
          window.event.returnValue = null;
      }    
      else{ //Firefox    
          e.preventDefault();
          return false;
      }     
 }
 else
    return; 
}

/***********************************************************************************************************
* Function:       validaCampo
*      Retira todos os caracteres n�o-num�ricos de um campo. Esta fun��o visa suprimir um problema apresentado
* pela fun��o <validaDigito> (o texto n�o � validado quando o mesmo � colado atrav�s do comando ctrl+v).
*
* Autor:
*       Farley Cruz dos Santos (estagi�rio SEQUAS) em 31/01/2008	
*
* Par�metros:	 
*	idCampo - o campo a ser validado
*
* Observa��o:
*       Esta fun��o � chamada internamente pela fun��o <validaDigito>. 
*
************************************************************************************************************/

function validaCampo(idCampo){
  campo = document.getElementById(idCampo);
  texto = campo.value;
  novoTexto = '';
  
  for(i=0;i<texto.length;i++){
      if("0123456789".indexOf(texto.charAt(i)) != -1){
          novoTexto+=texto.charAt(i);
      }
  }
  campo.value = novoTexto;
}

/***********************************************************************************************************
* Function:       validaEmail
*      Garante que o e-mail seja digitado com a sintaxe correta
*      (char@char.char). Char � um conjunto de caracteres permitidos.  
*
* Autor:  
*		Google (free), pesquisado pela equipe do Sesad
*	
*
* Parametros:  
*		campo do formul�rio que ser� validado
*			  
*			  
*
* Exemplo: 
*	> onblur="validaEmail(this);"  
*	    
* Data publica��o: 
*		16/03/2007 por Larissa
************************************************************************************************************/
function validaEmail(campo){    
  var str = campo.value;
  
  if (window.RegExp) {
      var reg1str = "(@.*@)|(\\.\\.)|(@\\.)|(\\.@)|(^\\.)";
      var reg2str = "^.+\\@(\\[?)[a-zA-Z0-9\\-\\.]+\\.([a-zA-Z]{2,3}|[0-9]{1,3})(\\]?)$";
      var reg1 = new RegExp(reg1str);
      var reg2 = new RegExp(reg2str);
      
      if (!reg1.test(str) && reg2.test(str)) {
          return true;
      }
      else {       
			if (campo.value != ""){
			alert ("E-mail incorreto!\rVerifique e digite novamente.");
          campo.focus();
          campo.select();
          return false;        
      }}
  }else{
      if(str.indexOf("@") >= 0){
          return true;
      }        
      campo.focus();
      campo.select();
      return false;
  }
}


/***********************************************************************************************************
* Function:       teclaAltera
*      Verifica se a tecla n�o altera o conte�do do campo texto. S�o teclas como Home, End e Tab
*
* Autor:
*	
*	      -
*
*
* Par�metros:
*	
*			  e - evento
************************************************************************************************************/

function teclaAltera(e) {
    if (((e.keyCode >= 35) && (e.keyCode <= 40)) || (e.keyCode == 9)) { // setas, Home, End e tab
	  return false;
    } else {
	  return true;
    }

}



/***********************************************************************************************************
* Function:       todosOsCamposPreenchidos
*      Verifica se todos os campos do tipo 'text', 'textarea' e 'file' do form 'frm' est�o preenchidos.
*			  Se n�o estiverem, aparece um aviso com o texto informado no par�metro 'mensagem'.
*
* Autor:
*	
*	      Rafael em 19/11/2001
*
*
* parametros: 
*			  frm      - form a ser verificada
*			  mensagem - mensagem de aviso
*
* Exemplo:
*		>  <form onSubmit="return todosOsCamposPreenchidos(this,'Preencha todos os campos.')">
************************************************************************************************************/

function todosOsCamposPreenchidos(frm, mensagem) {
var elemento;
for (var i = 0; i < frm.elements.length; i++) {
	elemento = frm.elements[i];
	if ((elemento.type == "text") || (elemento.type == "textarea")
          || (elemento.type == "file")) {
		if (elemento.value == "") {
		   alert(mensagem);
		   return false;
		}
	}
}
return true;
}



/***********************************************************************************************************
* Function:       validaCaractere
*     Permite a digita��o somente de caracteres v�lidos. Impede a digita��o de aspas (simpes e duplas), do
*  caractere % (percentual) e da seq��ncia Ctrl+V.
*
* Autor: 
*       Marcelo Ferreira da Silva (estagi�rio SESAD) em 15/02/2008	
*
* Par�metros:	 
*	    e - evento
*
* Exemplo
*       > <input type="text" onkeydown="return validaCaractere(event);"/>
************************************************************************************************************/

function validaCaractere(e)
{ 
 var tecla; 
 var percent = false;

 // Pega o keycode da tecla que o usu�rio pressionou   
 if(window.event){ //Internet Explorer
      tecla   = e.keyCode;       
 }
 else{ //Demais browsers
      tecla = e.which;
 }  
 var caracter = String.fromCharCode(tecla);   	

 //Verificando se foi digitado o caractere percent (%)
 if(e.shiftKey && tecla == 53){
      percent = true;
 }
	
 if (!percent && tecla != 192 && !(e.ctrlKey && tecla == 86))//verifica se � diferente de aspas, percent e Ctrl+V
 {
    return; 
 }else { //Impedindo a digita��o de caracteres inv�lidos
      if (window.event){ //IE       
          window.event.returnValue = null;
      }    
      else{ //Firefox    
          e.preventDefault();
          return false;
      }
  } 
}


/***********************************************************************************************************
* Function:       validaIntervalo
*      Valida um campo de texto de acordo com o n�mero m�nimo e m�ximo de caracteres que este campo pode
*      receber.
*
* Autor:
*	
*	      -
*
* Par�metros:
*	 
*			  campo - campo a ser validado
*			  event - evento
*			  min   - tamanho m�nimo
*			  max   - tamanho m�ximo
************************************************************************************************************/

function validaIntervalo(campo, event,min,max)
{
	if (!validaDigito(event)) return false;

	// setas, Home, End e tab
	if (((event.keyCode >= 35) && (event.keyCode <= 40)) || (event.keyCode == 9))
	{ 
   	  return true;
   	}

	// Backspace e delete
	if ((event.keyCode == 8) ||  (event.keyCode == 46)) 
	{ 
	     return true;
	}

	var c = event.keyCode % 96
	c = c % 48;
	var texto = campo.value;
	for (var i = 0; campo.value.charAt(i) == '0'; i++);
  	texto = texto.substr(i, texto.length);
	var num = parseInt(texto + c,10);
	//window.alert ('Num: '+num);
	if ((num < parseInt(min,10)) || (num > parseInt(max,10))) return false;
	else return true;
}



//Group: Bloqueio ou limita��o

/***********************************************************************************************************
* Function:       desabilitaBotaoInversoDoMouse
*
*
*      Desabilita o bot�o inverso do Mouse. Emite uma mensagem informando que o recurso do bot�o
*             foi desabilitado
*
* Par�metros:
*	 
*			  mouseButton - bot�o do mouse que foi pressionado
*
* Autor:
*	
*	      
*			  Rog�rio Nakegawa em 04/01/2006
************************************************************************************************************/


function desabilitaBotaoInversoDoMouse(mouseButton)
{
	if(mouseButton > 1)
	{
		alert('Este recurso foi desabilitado.');
	}
}

/***********************************************************************************************************
* Function:   desabilitaCtrlV
*      Desabilita o comando ctrl+V
*
* Autor:
*	
*	      Jo�o Luiz e Roberto Diener em 29/07/2005
*
*	      Documentado em 14/03/2007 por Larissa e Farley, altera��o de nome de "fncKeyStop" para "desabilitaCtrlV"
*
* Par�metros:
*	
*	 nenhum
*
* Exemplo:
*	   > <INPUT ... onkeyup="return desabilitaCtrlV()" onkeydown="return desabilitaCtrlV()">
************************************************************************************************************/

function desabilitaCtrlV() 
{
	// Verifica se a tecla ctrl est� pressionada.
	// If the Netscape way won't work (event.modifiers is undefined),
	// try the IE way (event.ctrlKey)
	var ctrl = typeof event.modifiers == 'undefined' ?
	event.ctrlKey : event.modifiers & Event.CONTROL_MASK;

	// Verifica se a tecla V est� pressionada.
	// If the Netscape way won't work (event.which is undefined),
	// try the IE way (event.keyCode)
	var v = typeof event.which == 'undefined' ?
	event.keyCode == 86 : event.which == 86;

	// Se as teclas ctrl e V estiverem pressionadas
	if ( ctrl && v ) 
	{
	     return false;
	}
	return true;
}

/***********************************************************************************************************
* Function:       limitaDigitacao
*           Limita a digita��o de um campo at� o tamanho especificado. Faz uma chamada interna a fun��o "limitaTamanhoTextArea".
*               Vale tanto para input text quanto textArea
* 
* Autor:
*     
*          Modifica��o para compatibilidade com Firefox em 22/03/2007 por Farley Cruz dos Santos (estagi�rio SEQUAS).
*          Modifica��o para retirar do campo texto os caracteres digitados al�m do permitido, por Fernando Mendon�a Maranho (SESEC)
*
* Par�metros:
*     
*                   campo  - campo que ser� limitado (objeto)
*                   num    - tamanho m�ximo do campo
*                         evento - evento em a��o no campo
*
* Exemplo:
*   > <input type="text" id="campoTexto" onKeyUp="limitaDigitacao(this,11,event)" onKeyDown="limitaDigitacao(this,11,event)"  />
*   > <h:inputTextarea id="campoTexto" onkeyup="limitaDigitacao(this,2000,event);" onkeydown="limitaDigitacao(this,2000,event);" />
************************************************************************************************************/

function limitaDigitacao(campo, num, evento)
{
 if(campo.value.length > num){
	  //Retira do campo texto os caracteres digitados al�m do permitido
	  campo.value = campo.value.substring(0, num);
 }
 return limitaTamanhoTextArea(num,campo.value.length,evento);
}


/***********************************************************************************************************
* Function:       limitaTamanhoTextArea
*      Limita o tamanho (quantidade de caracteres) de uma caixa de texto do tipo Textarea/textField.
*
* Autor:
*	
*	      C�ssimo em 07/11/2003.
*
*	      Documentado em 14/03/2007 por Larissa e Farley, altera��o de nome de "validaTamanhoTextArea" para "limitaTamanhoTextArea".
*
*             Modifica��o para compatibilidade com Firefox em 22/03/2007 por Farley Cruz dos Santos (estagi�rio SEQUAS).
*
*
* Par�metros:
*	 
*			  tamanhoMaximo             - tamanho m�ximo da caixa de texto
*			  qtdeCaracteresDigitados   - quantidade de caracteres digitados na caixa de texto
*                         evento                    - evento
*
* Exemplo:
*	   > <textarea name="textarea" cols="5" rows="3" onKeyDown="limitaTamanhoTextArea(100,document.frmAlterar.txtObjeto.value.length,event);"/>
************************************************************************************************************/

function limitaTamanhoTextArea(tamanhoMaximo,qtdeCaracteresDigitados,evento)
{
 // Pega a quantidade de caracteres digitados na caixa de texto Textarea
 var qtdeCaracteres = qtdeCaracteresDigitados;
 
 // Pega o valor ASCII da tecla que o usu�rio pressionou   
 if(window.event){ //Internet Explorer
      var tecla   = evento.keyCode;
 }
 else{ //Demais browsers
      var tecla = evento.which;
 } 

 // Teclas Insert, Del, Page UP, Page Down, Home, End, e setas de movimenta��o.
 if (tecla == 8 || tecla == 37 || tecla == 38 || tecla == 39 || 
     tecla == 40 || tecla == 46 || tecla == 36 || tecla == 35 || 
	   tecla == 33 || tecla == 34  || tecla == 45){
	    return; // Se for pressionada qualquer uma dessas teclas n�o se 
		        // verifica a quantidade de caracteres
 }
 
 // Teclas ALT e CTRL
 if (tecla == 17 || tecla == 18){
 		return; // Se for pressionada qualquer uma dessas teclas n�o se
		        // verifica a quantidade de caracteres	
 }

 if (qtdeCaracteres < tamanhoMaximo)
	    return; // se o limite de caracteres ainda n�o foi alcan�ado aceita-se a digita��o 
		        // do caractere 
 else{ // caso contr�rio,  ignora-se a digita��o do caractere
     if(window.event){ //Internet Explorer
         window.event.returnValue = null;
     }
     else{ //Demais browsers
         if(evento.returnValue){
              evento.returnValue = false;
              evento.cancelBubble = true;
         }
         if(evento.preventDefault){
             evento.preventDefault();
             evento.stopPropagation();
         }
     }
 }
}


//Group: Modifica��o de atributos

/***********************************************************************************************************
* Function:       swapColor
*	Muda a cor da linha de uma tabela. Seu acionamento pode ser dado atraves de uma
*	checkbox ou um radiobutton. A chamada para esta fun��o � feita de forma
* 	indireta. Para fazer uso dela deve-se chamar: ou a fun��o setColorCheckBoxGroup() ou 
*	a fun��o setColorRadioGroup().
*
*	Para o caso de um radiobutton JSF, deve-se fazer uso da fun��o swapColorJSF();
*
*
* Autor:
*	
*	     Gustavo de Carvalho Dantas (estagi�rio SESAD)em 02/03/2007
*
*
* Par�metros: 
*			  oCheckbox - Este par�metro � o atual radiobutton/checkbox. 
*
* Exemplo:
* 	> <input type= "radiobutton" onClick="swapColorRadioGroup()">
*	> <input type="checkbox" onClick="swapColorCheckboxGroup()">
************************************************************************************************************/   
function swapColor(oCheckbox) {
   var pop = oCheckbox; checkedcolor = '#FFF7D6';
	 if (pop != null) {
	     while (pop.nodeType != 1 || pop.nodeName.toLowerCase() != 'tr') 	             
				pop = pop.parentNode;	 
	     pop.style.backgroundColor = (oCheckbox.checked) ? checkedcolor : '';
	}
}

/***********************************************************************************************************
* Function:       swapColorJSF
*     Muda a cor de um checkbox/radiobutton, rederizado por JSF, que est� selecionado. Para o caso de
*             radiobutton, a chamada inicialmente deve ser feita � fun��o "setColorRadioGroupJSF ()"
*             e essa fun��o internamente faz a chamada a swapColorJSF(this).
*
* Autor:
*		 Gustavo de Carvalho Dantas (estagi�rio SESAD)em 02/03/2007
*
* Param�tros: 
*			  oCheckbox - Este par�metro � o atual radiobutton/checkbox.
*
* Exemplo:
*             > onClick="swapColor(this)";
*
* Observa��o: 
*			   Essa fun��o foi criada para suprir um problema que vem do uso de radiobuttons em JSF.
*			   Ao se adicionar um radiobutton, o jsf renderiza de forma automatica uma table em volta desse radio
*			   impossibilitando assim o correto funcionamento da fun��o swapColor. Nessas situa��es a fun��o
*			   swapColorJSF dever� ser utilizada.	
************************************************************************************************************/   

function swapColorJSF(oCheckbox) {
   var pop = oCheckbox; checkedcolor = '#FFF7D6';
   while (pop.nodeType != 1 || pop.nodeName.toLowerCase() != 'tr') 
   {
      pop = pop.parentNode;	         
   }    
   pop = pop.parentNode;
   while (pop.nodeType != 1 || pop.nodeName.toLowerCase() != 'tr') 
   {
      pop = pop.parentNode;
   }
   pop.style.backgroundColor = (oCheckbox.checked) ? checkedcolor : '';
}


/***********************************************************************************************************
* Function:       ocultaTagsSelect
*      Muda para "hidden" o atributo 'visibility' de todas as tags "select" do documento
*
* Autor:
*	
*	   Documentado em 14/03/2007 por Larissa e Farley, altera��o de nome de "hideSelect" para "ocultaTagsSelect"
*
* Par�metros: 
*		  -
************************************************************************************************************/

function ocultaTagsSelect() {
	if (navegador.isIE){
		if (document.all.length>0) {
			cSELECT = document.all.tags("SELECT");
				for (i=0;i<cSELECT.length;i++) {
					if (cSELECT(i).style.visibility != 'hidden'){
						visibilidadeCampo[i] = 'visible';
					}
					else{
						visibilidadeCampo[i] = 'hidden';
					}
					
					cSELECT(i).style.visibility='hidden';	
					vetor[i]=cSELECT(i);
				}
		}
	}
}


/***********************************************************************************************************
* Function:       exibeTagsSelect
*      Muda para "visible" o atributo 'visibility' de todas as tags "select" do documento. � utilizada em conjunto
*	   com a fun��o "ocultaTagsSelect", que deve ser chamada anteriormente - amarra��o de vari�veis.
*
* Autor:
*	
*	   Documentado em 14/03/2007 por Larissa e Farley, altera��o de nome de "showSelect" para "exibeTagsSelect"
*
* Par�metros: 
*		  -
************************************************************************************************************/

function exibeTagsSelects() {
	if (navegador.isIE){
		if (document.all.length>0) {
			for (i=0;i<vetor.length;i++){
				vetor[i].style.visibility=visibilidadeCampo[i];
			}
		}
	}
}


//Group: Funcionalidades de 'String' e 'Data'

/***********************************************************************************************************
* Function: right
*      Recupera os caracteres mais � direita de uma string.   
*
* Autor:
*      Marcos Vieira em 19/03/2007
*
* Parametros: 
*      p_str - string da qual ser� extra�da a sua parte mais � direita;
*      p_qtd - quantidade de caracteres a ser extra�do da string (tamanho da string retornada);
*      p_char - caracter que ser� usado para completar a string at� o tamanho desejado. Branco se omitido;
*
* Exemplo:
*      > document.write(right('SEQUAS', 2)); // AS
*      > document.write(right('SEQUAS', 10, '.')) // ....SEQUAS
************************************************************************************************************/

function right(p_str, p_qtd, p_char) {

	// se caracter para completar n�o informado, assume branco
	if (! p_char) {
	   p_char = ' ';
	}
	
	// recupera os caracteres mais � direita da string original
	var aux = '';
	var i = p_str.length;
	while ((i > 0) && (aux.length < p_qtd)) {
	   aux = p_str.substring(i-1, i) + aux;
	   i--;
	}
	
	// completa parte mais � direita com caracter de completar
	while (aux.length < p_qtd) {
	   aux = p_char + aux;
	}
	
	return aux;
}

/***********************************************************************************************************
* Function: montaData
*      Recebe uma data no formato d/m/aaaa, d/m/aa, d-m-aaaa, d-m-aa, separando suas partes
*      (dia,m�s,ano) e retornando-as num vetor (0-dia, 1-m�s, 2-ano).
*
* Autor:
*      Adapta��o Marcos Vieira em 19/03/2007 (free na Internet)
*
* Parametros: 
*      p_data - data string nos formatos d/m/aaaa, d/m/aa, d-m-aaaa, d-m-aa, onde o dia (d) e o m�s (m) 
*      podem ou n�o serem precedidos por '0' (zero), e se o ano for curso (aa), ser� assumido '19'.
*
* Exemplo:
*      > var v_data = montaData('30/12/2006');
*      > document.write('Dia: ', v_data[0], 'M�s: ', v_data[1], 'Ano: ', v_data[2]);
************************************************************************************************************/

function montaData( p_data ) {
 var diasMeses = new Array(0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31);
 var v_exp = /(0?[1-9]|[12][0-9]|3[01])[\-\/](1[0-2]|0?[1-9])[\-\/]((19|20)?\d{2})$/;

 if ( v_exp.test( p_data ) ) {
    var v_data = v_exp.exec( p_data );
    var v_dia = v_data[ 1 ];
    var v_mes = v_data[ 2 ];
    var v_ano = ( v_data[ 4 ] ) ? v_data[ 3 ] : '19' + v_data[ 3 ];

    diasMeses[2] = (( v_ano % 4 == 0 ) && (( v_ano % 100 != 0 ) || ( v_ano % 400 == 0 ))) ? 29 : 28;

    if ( v_dia <= diasMeses[ parseInt(v_mes,10) ] )
       return [ v_dia, v_mes, v_ano ];
 }

 return null;
}  

/***********************************************************************************************************
* Function: comparaData
*      Compara duas datas usando o operador desejado, retornando true caso a compara��o
*      seja verdadeira. Se uma das datas for inv�lida, retorna false.
*
* Autor:
*      Adapta��o Marcos Vieira em 19/03/2007 (free na Internet)
*
* Parametros: 
*      p_dtPrim - primeira data string nos formatos d/m/aaaa, d/m/aa, d-m-aaaa, d-m-aa, onde o dia (d) e o m�s (m) 
*      podem ou n�o serem precedidos por '0' (zero), e se o ano for curso (aa), ser� assumido '19';
*      p_dtSeg - segunda data string nos formatos d/m/aaaa, d/m/aa, d-m-aaaa, d-m-aa, onde o dia (d) e o m�s (m) 
*      podem ou n�o serem precedidos por '0' (zero), e se o ano for curso (aa), ser� assumido '19';
*      p_oper - comparador usado comparar as duas datas (<, >, <=, >=, ==, !=). Se omitido, ser� usado '<';
*
* Exemplo:
*     > if (comparaData('1/1/2007', '01-01-2007', '==') {
*     >    document.write('datas s�o iguais');
*     > }
************************************************************************************************************/

function comparaData( p_dtPrim, p_dtSeg, p_oper ) {
 var v_prim  = montaData( p_dtPrim );
 var v_seg   = montaData( p_dtSeg );

 if ( !v_prim || !v_seg ) return false;

 var v_data1 = v_prim[ 2 ] + right(v_prim[ 1 ], 2, '0') + right(v_prim[ 0 ], 2, '0');
 var v_data2 = v_seg[ 2 ] + right(v_seg[ 1 ], 2, '0') + right(v_seg[ 0 ], 2, '0');
 
 return eval(v_data1 + (( p_oper ) ? p_oper : '<') + v_data2);
}

/***********************************************************************************************************
* Function:       abreCalendario
*   Abre um calend�rio para a sele��o de uma data. O calend�rio ser� aberto debaixo do campo e clicando em 
*	um dia a data selecionada � colocada no campo no formato dd/mm/aaaa. Faz chamadas internas a v�rias fun��es.
*
* Autor:
*	Adapta��o por Farley Cruz dos Santos e Marcelo Ferreira Hallac (estagi�rios SEQUAS) em 13/04/07 (free na Internet).
*
* Parametros: 
*	campo  - campo (objeto)
*	evento - evento do campo
*
* Exemplo:
*	   > <input type="text" value="dd/mm/aaaa" onfocus="abreCalendario(this,event)" onclick="abreCalendario(this,event)">
*
************************************************************************************************************/
function abreCalendario(campo,evento){
	evento.cancelBubble=true;
	campo.select();
	lcs(campo);
}


function getObj(objID)
{
  if (document.getElementById) {return document.getElementById(objID);}
  else if (document.all) {return document.all[objID];}
  else if (document.layers) {return document.layers[objID];}
}

function checkClick(e) {
	e?evt=e:evt=event;
	CSE=evt.target?evt.target:evt.srcElement;
	if (getObj('fc'))
		if (!isChild(CSE,getObj('fc'))){
			getObj('fc').style.display='none';
                      document.getElementById("iFrameCalendario").style.visibility='hidden';
              }
}

function escondeCalendario(e){
	e?evt=e:evt=event;
	tecla = evt.keyCode?evt.keyCode:evt.which;
	if(tecla==13||tecla==9){ //13 - enter, 9 - tab
		getObj('fc').style.display='none';
              document.getElementById("iFrameCalendario").style.visibility='hidden';
      }
}

function isChild(s,d) {
	while(s) {
		if (s==d) 
			return true;
		s=s.parentNode;
	}
	return false;
}

function Left(obj)
{
	var curleft = 0;
	if (obj.offsetParent)
	{
		while (obj.offsetParent)
		{
			curleft += obj.offsetLeft
			obj = obj.offsetParent;
		}
	}
	else if (obj.x)
		curleft += obj.x;
	return curleft;
}

function Top(obj)
{
	var curtop = 0;
	if (obj.offsetParent)
	{
		while (obj.offsetParent)
		{
			curtop += obj.offsetTop
			obj = obj.offsetParent;
		}
	}
	else if (obj.y)
		curtop += obj.y;
	return curtop;
}

//Essa altera��o foi retornada � vers�o anterior: problemas com tempo de apresenta��o da p�gina
//estavam provocando desconforto visual �s janelas modais, telas de confirma��o e encolhimento
//de abas -- com o retorno, o problema do backspace duplo volta
//Lembrar que isso � apenas solu��o de contorno.
//------------------------------------------------------------------
//A URL do nada.html abaixo foi alterada para https://contas.tcu.gov.br/tcu/Web/Shared/paginas/nada.html porque do jeito que estava (Shared/paginas/nada.html)  poderia ocorrer "p�gina n�o encontrada".
//Esse artif�cio � necess�rio devido a um bug do IE6. Favor n�o remover.
//document.write('<iframe id="iFrameCalendario" src="Shared/paginas/nada.html"  style="visibility:hidden; position:absolute; z-index:19"></iframe>');

//P�gina utilizada quando n�o h� p�gina anterior
var urlNada = "https://contas.tcu.gov.br/sagas/Web/Shared/paginas/nada.html";
//Obter a p�gina de onde veio o usu�rio
var paginaAnterior = document.referrer;
//Verificar se h� p�gina anterior
if (paginaAnterior && paginaAnterior != "") {
    // Separar a string pelo delimitador "/"   
    var trechosUrl = paginaAnterior.split("/");
    urlNada = trechosUrl[0] + "//" + trechosUrl[2] + "/" + trechosUrl[3] + "/Web/Shared/paginas/nada.html";   
}
document.write('<iframe id="iFrameCalendario" src="' + urlNada + '"  style="visibility:hidden; position:absolute; z-index:19"></iframe>');

document.write('<table id="fc" style="position:absolute;border-collapse:collapse;border:1px solid #cccccc;display:none; z-index:20;" cellpadding=2>');
document.write('<tr><td class="linhaMesAnoSetas" onclick="csubm()"><</td><td colspan=5 id="mns" class="linhaMesAnoSetas"></td><td class="linhaMesAnoSetas" onclick="caddm()">></td></tr>');
document.write('<tr><td class="linhaDiasSemana">D</td><td class="linhaDiasSemana">S</td><td class="linhaDiasSemana">T</td><td class="linhaDiasSemana">Q</td><td class="linhaDiasSemana">Q</td><td class="linhaDiasSemana">S</td><td class="linhaDiasSemana">S</td></tr>');
for(var kk=1;kk<=6;kk++) {
	document.write('<tr>');
	for(var tt=1;tt<=7;tt++) {
		num=7 * (kk-1) - (-tt);
		document.write('<td id="v' + num + '" style="width:18px;height:18px">&nbsp;</td>');
	}
	document.write('</tr>');
}
document.write('</table>');

document.all?document.attachEvent('onclick',checkClick):document.addEventListener('click',checkClick,false);
document.all?document.attachEvent('onkeydown',escondeCalendario):document.addEventListener('keydown',escondeCalendario,false);


//Script do calend�rio
var now = new Date;
var sccm=now.getMonth();
var sccy=now.getFullYear();
var ccm=now.getMonth();
var ccy=now.getFullYear();

var updobj;
function lcs(ielem) {
	updobj=ielem;
	getObj('fc').style.left=Left(ielem)+'px';
	getObj('fc').style.top=Top(ielem)+ielem.offsetHeight+'px';
	getObj('fc').style.display='';
	calendario = getObj('fc');
      if (browser.isIE) {
          x = Left(calendario);
          y = Top(calendario);
          
          w = calendario.offsetWidth;
          h = calendario.offsetHeight;
        
          frameM = document.getElementById("iFrameCalendario");
          
          frameM.style.left = x + "px";
          frameM.style.top  = y + "px";
          frameM.style.width = w + "px";
          frameM.style.height  = h + "px";
          
          frameM.style.visibility = "visible";
      }
      
	// Checando se a data � v�lida
	curdt=ielem.value;
	curdtarr=curdt.split('/');
	isdt=true;
      
      // atribui��o do ano digitado ao calend�rio: s� pode haver atribui��o se o campo estiver preenchido
	if(sccy!=curdtarr[2] && ielem.value != '') 
		sccy = curdtarr[2];
	for(var k=0;k<curdtarr.length;k++) {
		if (isNaN(curdtarr[k])){
			curdtarr[1]=now.getMonth()+1;
                      curdtarr[2]=now.getFullYear();
                      if(sccy!=curdtarr[2] && ielem.value != '') 
                          sccy = curdtarr[2];
              }
	}
	if (isdt&(curdtarr.length==3)) {
		ccm=curdtarr[1]-1;
		ccy=curdtarr[2];
		prepcalendar(curdtarr[0],curdtarr[1]-1,curdtarr[2]);
	}
}

function evtTgt(e)
{
	var el;
	if(e.target)el=e.target;
	else if(e.srcElement)el=e.srcElement;
	if(el.nodeType==3)el=el.parentNode; // defeat Safari bug
	return el;
}
function EvtObj(e){if(!e)e=window.event;return e;}

//Troca o estilo do dia quando o mesmo recebe o focus do mouse
function cs_over(e) {
	evtTgt(EvtObj(e)).className='diaFocus'; //Cor dia focus
}

//Troca o estilo do dia quando o mesmo perde o focus do mouse
function cs_out(e) {
	evtTgt(EvtObj(e)).className='diaBlur'; //Cor do background dia blur
}

//Copia no campo a data selecionada e esconde o calend�rio
function cs_click(e) {
	updobj.value=calvalarr[evtTgt(EvtObj(e)).id.substring(1,evtTgt(EvtObj(e)).id.length)]; 
	getObj('fc').style.display='none';
      document.getElementById("iFrameCalendario").style.visibility='hidden';;
	
}

var mn=new Array('JAN','FEV','MAR','ABR','MAI','JUN','JUL','AGO','SET','OUT','NOV','DEZ');
var mnn=new Array('31','28','31','30','31','30','31','31','30','31','30','31');
var mnl=new Array('31','29','31','30','31','30','31','31','30','31','30','31');
var calvalarr=new Array(42);

function f_cps(obj) { //Dias v�lidos
	obj.className='diasValidos';
}

function f_cpps(obj) { //Dias inv�lidos
	obj.className='diasInvalidos';
}

function f_hds(obj) { //Dia selecionado
	obj.className='diaSelecionado';
}

//day selected
function prepcalendar(hd,cm,cy) {
	now=new Date();
	sd=now.getDate();
	td=new Date();
	td.setDate(1);
	td.setFullYear(cy);
	td.setMonth(cm);
	cd=td.getDay();
	getObj('mns').innerHTML=mn[cm]+ ' ' + cy;
	marr=((cy%4)==0)?mnl:mnn;
	for(var d=1;d<=42;d++) {
		f_cps(getObj('v'+parseInt(d)));
		if ((d >= (cd -(-1))) && (d<=cd-(-marr[cm]))) {
			dip=((d-cd < sd)&&(cm==sccm)&&(cy==sccy));
			dip=false;
			htd=((hd!='')&&(d-cd==hd));
			if (dip)
				f_cpps(getObj('v'+parseInt(d)));
			else if (htd)
				f_hds(getObj('v'+parseInt(d)));
			else
				f_cps(getObj('v'+parseInt(d)));
			getObj('v'+parseInt(d)).onmouseover=(dip)?null:cs_over;
			getObj('v'+parseInt(d)).onmouseout=(dip)?null:cs_out;
			getObj('v'+parseInt(d)).onclick=(dip)?null:cs_click;
			getObj('v'+parseInt(d)).innerHTML=d-cd;	
			                        var sub=d-cd; 
                      var dia = sub.toString();
                      if (dia.length == 1) 
                          dia = '0'+dia
                      getObj('v'+parseInt(d)).innerHTML=dia;	
                      // insere '0's � esquerda dos meses
                      sub = cm-(-1);
                      var mes = sub.toString();
                      if (mes.length == 1)
                          mes = '0'+mes
			calvalarr[d]=''+dia+'/'+mes+'/'+cy;
		}
		else {
			getObj('v'+d).innerHTML='&nbsp;';
			getObj('v'+parseInt(d)).onmouseover=null;
			getObj('v'+parseInt(d)).onmouseout=null;
			getObj('v'+parseInt(d)).style.cursor='default';
			}
	}
}

prepcalendar('',ccm,sccy);

function caddm() {
	marr=((ccy%4)==0)?mnl:mnn;
	ccm+=1;
	if (ccm>=12) {
		ccm=0;
		sccy++;
	}
	cdayf();
	prepcalendar('',ccm,sccy);
}

function csubm() {
	marr=((ccy%4)==0)?mnl:mnn;
	ccm-=1;
	if (ccm<0) {
		ccm=11;
		sccy--;
	}
	cdayf();
	prepcalendar('',ccm,sccy);
}

function cdayf() {
if ((ccy>sccy)|((ccy==sccy)&&(ccm>=sccm)))
	return;
else {
	ccy=sccy;
	sccm=ccm;
	}
}


//Group: Funcionalidades de 'div'

/***********************************************************************************************************
* Function:       showDiv
*      Torna vis�vel uma div atrav�s do id.
*
* Autor:
*	
*	      Marcos Viera em 02/10/2006
*
* Par�metros:
*	 
*			  id - id do div
************************************************************************************************************/

function showDiv(id) {
	changeDisplayDiv(id, "block");
}


/***********************************************************************************************************
* Function:       hiddenDiv
*      Torna escondido uma atrav�s do id.
*
* Autor:
*	
*	      Marcos Vieira em 02/10/2006
*
* Par�metros:
*	 
*			  id - id do div
************************************************************************************************************/

function hiddenDiv(id) {
	changeDisplayDiv(id, "none");
}


/***********************************************************************************************************
* Function:       changeDisplayDiv
*      Altera o estado de visibilidade de uma div atrav�s do 'id'.
*
* Autor:
*	
*	      Marcos Vieira em 02/10/2006
*
* Par�metros:
*	
*			  lstId  - -
*			  action - -
*
* Observa��o: 
*        "action" pode ser "block", para tornar vis�vel, ou "none", para tornar invis�vel
************************************************************************************************************/

function changeDisplayDiv(lstId, action) {
 var divs = document.getElementsByTagName("DIV");
 var id = null;

 do {
 		if ((lstId != null) && (lstId.length != 0)) {
 			var pos = lstId.indexOf(",");
 			if (pos != -1) {
 			   id = lstId.substring(0, pos);
 			   lstId = lstId.substr(pos + 1);
 			} else {
 			   id = lstId;
 			   lstId = null;
 			}
 		}

	   for (i = 0; i < divs.length; i++) {
   		if ((divs[i].id != null) &&
            ((id == null) || (id.length == 0) || (divs[i].id.substring(0, id.length) == id))) {
		 		divs[i].style.display = action;
		   }
 	   }
 } while ((lstId != null) && (lstId.length != 0));
}

var browser = new Navegador();
/***********************************************************************************************************
* Function:       exibeTelaEspera
*      Exibe uma tela de espera. Esta tela sobrep�e a tela atual, impedindo qualquer a��o na mesma. 
* Visualmente escurece a tela e exibe um icone "carregando". 
*
* Autor:
*	
*	      Farley Cruz dos Santos (estagi�rio SEQUAS) em 14/06/2007
*
*             Corre��o de problema com submit em 18/07/2007
*
*             Corre��o de problema com recarregamento da imagem em 24/08/2007
*
*             Inclus�o de argumento referente ao endere�o da imagem em 05/10/2007
*
* Par�metros: 
*	    endereco - endere�o relativo do arquivo 'imgIconeEspera.gif'
*
* Exemplos:
*           > <input type="button" value="Enviar" class="botoes" onclick="exibeTelaEspera()"/>
*           > <input type="button" value="Enviar" class="botoes" onclick="exibeTelaEspera('../Shared/imagens/imgIconeEspera.gif')"/>
*
* Observa��o:
*       Se n�o for passado nenhum endere�o como argumento, a fun��o automaticamente tentar� encontrar o endere�o da imagem.
* Caso n�o funcione sem o argumento, tentar passar o endere�o relativo da imagem como argumento.
************************************************************************************************************/
enderecoImgTelaEspera = null;

function exibeTelaEspera(endereco){
  frame = document.getElementById('telaEspera');
	//Seleciona todos os inputs do documento:
	inputs = document.getElementsByTagName("input");
  document.getElementById('imgIndicador').style.display='';
  frame.style.display = "";
  frame.style.zIndex = "3";
  var i=0;
	
	if(browser.isIE){ //esconde as combos no IE
      selects = document.getElementsByTagName("select");
      for(aux=0; aux<selects.length;aux++){
          selects[aux].style.visibility="hidden";
      }
  }
	
	//Esconde os inputs do documento:
    for(i=0;i<inputs.length;i++){
          var thisElement = inputs[i];
       if (thisElement.getAttribute("type") == "submit" || thisElement.getAttribute("type") == "image")
            thisElement.style.visibility="hidden";
    }
	
  if(endereco){ //Com argumento
      enderecoImgTelaEspera = endereco;
      setTimeout("document.getElementById('imgIndicador').src = enderecoImgTelaEspera;",100); //Corrige a anima��o da imagem ap�s um submit
  }
  else{ //Sem argumento
      setTimeout('atualizaImg()',100); 
  }  
}

function atualizaImg(){
  url = document.URL;
  pastas = url.split('/');
  numPastas = 0;
  aux = pastas.length-2;
  verifica = true;
  while(verifica){
      if(pastas[aux]=='Web'){
          verifica=false;
      }
      else{
          numPastas++;
          aux--;
      }
  }
  endereco = '';
  for(cont=0; cont<numPastas;cont++){
      endereco += '../';
  }
  endereco += 'Shared/imagens/imgIconeEspera.gif';
  document.getElementById('imgIndicador').src = endereco;
  
}




/***********************************************************************************************************
* Function:       escondeTelaEspera
*      Esconde a tela de espera.  
*
* Autor:
*	
*	      Farley Cruz dos Santos (estagi�rio SEQUAS) em 14/06/2007
*
* Par�metros: 
*		  Nenhum
************************************************************************************************************/
function escondeTelaEspera(){
  frame = document.getElementById('telaEspera');
  frame.style.display = "none";
  document.getElementById('imgIndicador').style.display='none';
  if(browser.isIE){ //reexibe as combos no IE6
      selects = document.getElementsByTagName("select");
      for(aux=0; aux<selects.length;aux++){ 
          selects[aux].style.visibility="visible";
      }
  }
}



var alturaDiv;

/***********************************************************************************************************
* Function:       adaptaDiv
*   Adapta a altura de uma div para que a mesma ocupe toda a �rea �til da tela. A div � adaptada assim que a
* p�gina � carregada e no redimensionamento da janela. 
*
* Autor:
*	Danilo Filgueira Mendon�a (estagi�rio SESOC) e Farley Cruz dos Santos (estagi�rio SEQUAS) em 24/08/07.
*
* Parametros: 
*       -
*
************************************************************************************************************/

function adaptaDiv(){

  var areaUtil = dimensoesInternas();
  var divs = document.getElementsByTagName('div');
  var divConteudo;
  var divRodape;
  var tamMinimo = 90;
	var calculo=0;
  
  for(aux=0;aux<divs.length;aux++){ //Recuperando a div de conte�do e a div de rodap�
      if(divs[aux].id.indexOf('divConteudo')!=-1){
          divConteudo = divs[aux];
      }
      else if(divs[aux].id.indexOf('divRodape')!=-1){
          divRodape = divs[aux];
      }
  }
  if(divConteudo && divRodape){
      if(!alturaDiv){ //Guardando a altura inicial da div
          alturaDiv = divConteudo.offsetHeight;
      }
      if((alturaDiv+recuperaPosicaoElemento(divConteudo).y)>areaUtil[1]){ //Verificando se a altura da div � maior que a �rea �til do browser
          divConteudo.style.overflowY='auto'; 
         	divConteudo.style.overflowX='hidden';
          if (browser.isIE) {
				calculo = (areaUtil[1] - recuperaPosicaoElemento(divConteudo).y - (divRodape.offsetHeight) - 20);
				//Verifica se o tamanho da tela �til � menor que o tamanho m�nimo permitido.
				if (calculo < tamMinimo) {				
					//Se o tamanho da �rea �til for menor que o tamanho permitido, ent�o seta o valor da altura da div com o tamanho m�nimo e retorna false;
					divConteudo.style.height = tamMinimo+'px';
					return false;
              }else{
				  //Caso contr�rio, a tavle rol�vel assume seu valor anteriormente calculado:
                divConteudo.style.height=(calculo)+'px';
                tabelas = divConteudo.getElementsByTagName('TABLE');
                      for(i=0;i<tabelas.length;i++){
                           tabelas[i].style.width='98%';
                      }
                }
          }
          else{
              divConteudo.style.height=(areaUtil[1]-recuperaPosicaoElemento(divConteudo).y-(divRodape.offsetHeight)-5)+'px';
          }
      }
      else{
          divConteudo.style.overflowY='';
          if(browser.isIE){
              divConteudo.style.height='auto';
          }
          else{
              divConteudo.style.overflowX='none'; //Escondendo a barra de rolagem horizontal
              divConteudo.style.height='auto';
          }
      }
  }
}

/***********************************************************************************************************
* Function:       constroiTabelaRedimensionavel
*   Transforma uma tabela comum em uma tabela redimension�vel. Tem como caracter�sticas fixar o cabe�alho e
* rodap� da tabela, rolando somente o conte�do da mesma. 
*
* Autor:
*	Danilo Filgueira Mendon�a (estagi�rio SESOC) e Farley Cruz dos Santos (estagi�rio SEQUAS) em 22/10/07.
*
* Parametros: 
*       -
*
************************************************************************************************************/

function constroiTabelaRedimensionavel(){
  
  /*
  Funcionamento da fun��o:
  -Recupera as divs (divCabecalho e divConteudo) e tabelas necess�rias
  -Copia o cabe�alho da tabela para dentro da divCabecalho
  -Esconde o cabe�alho na tabela original
  -Adapta o tamanho da tabela de acordo com a �rea dispon�vel no browser
  
  Deve ser chamada no evento window.onload
  */
  
  
  if(browser.isIE){
      adaptaDiv();
      if(browser.version==7){
      	//adaptaDiv();
      }
  }
  
  //recupera tabelas da p�gina
  var tabelas = document.getElementsByTagName('table');
  for(var i = 0; i < tabelas.length; i++){
      if(tabelas[i].id.indexOf('tabelaConteudo') == -1){
          continue;
      }
      else{
          //identificando divCabecalho vazio
          var divs = document.getElementsByTagName('div');
          for (var d= 0; d < divs.length; d++){
              if(divs[d].id.indexOf('divCabecalho') != -1){
                  if(divs[d].getElementsByTagName('th').length == 0){
                      var divCabecalho = divs[d];
                      break;
                  }
              } 
          }
          //identificando tabela conteudo que perdera o cabecalho posteriormente
          var tabelaConteudo = tabelas[i];
          //criando componentes da tabelaCabecalho;       
          var tabCabecalho = document.createElement('table');
          var thead = document.createElement('thead');
          var tr = document.createElement('tr');
          var th = document.createElement('th');
          tabCabecalho.appendChild(thead);
          thead.appendChild(tr);
          //recupera THs do antigo cabe�alho
          var ths = tabelas[i].getElementsByTagName('th'); 
          for(var j = 0; j < ths.length; j++){
              var thVelho = ths[j];
              //verifica se h� borda e 'padding' para posteriormente retira-los da largura setada
              var borderPadding = 0;
              if (thVelho.currentStyle){ //IE
                  if(parseInt(thVelho.currentStyle['paddingLeft'],10))
                          borderPadding = borderPadding + parseInt(thVelho.currentStyle['paddingLeft']);
                  if(parseInt(thVelho.currentStyle['paddingRight'],10))
                          borderPadding = borderPadding + parseInt(thVelho.currentStyle['paddingRight']);
                  if(parseInt(thVelho.currentStyle['borderLeftWidth'],10))
                          borderPadding = borderPadding + parseInt(thVelho.currentStyle['borderLeftWidth']);
                  if(parseInt(thVelho.currentStyle['borderRightWidth'],10))
                          borderPadding = borderPadding + parseInt(thVelho.currentStyle['borderRightWidth']);
              } //fim do if
              if (window.getComputedStyle){ //W3C
                  if(parseInt(window.getComputedStyle(thVelho,"").getPropertyValue('padding-left')))
                          borderPadding = borderPadding + parseInt(window.getComputedStyle(thVelho,"").getPropertyValue('padding-left'));
                  if(parseInt(window.getComputedStyle(thVelho,"").getPropertyValue('padding-right')))
                          borderPadding = borderPadding + parseInt(window.getComputedStyle(thVelho,"").getPropertyValue('padding-right'));
                  if(parseInt(window.getComputedStyle(thVelho,"").getPropertyValue('border-left-width')))
                          borderPadding = borderPadding + parseInt(window.getComputedStyle(thVelho,"").getPropertyValue('border-left-width'));
                  if(parseInt(window.getComputedStyle(thVelho,"").getPropertyValue('border-right-width')))
                          borderPadding = borderPadding + parseInt(window.getComputedStyle(thVelho,"").getPropertyValue('border-right-width'));
              } //fim do if
              //clona elemento velho com os nodos
              th = thVelho.cloneNode(true);
              //retira 'borderPadding' da largura
              th.width = thVelho.offsetWidth - borderPadding;
              // repassa o valor da largura para que essa fique fixada
              thVelho.width = th.width;
              // retira conteudo do thVelho
              thVelho.innerHTML = "";
              // retira borda do th velho
              thVelho.style.border = "none";
              //inclui elemento dentro da linha
              tr.appendChild(th);
          } //fim do for
          //passa atributos da tabela pai para a tabela cabecalho
          if(tabelaConteudo.border != "")
              tabCabecalho.border = tabelaConteudo.border;
          if(tabelaConteudo.cellSpacing != "")
              tabCabecalho.cellSpacing =  tabelaConteudo.cellSpacing;
          if(tabelaConteudo.cellPadding != "")
              tabCabecalho.cellPadding = tabelaConteudo.cellPadding;
          if(tabelaConteudo.className != "")
              tabCabecalho.className = tabelaConteudo.className;
          
          //inclui tabelaCabecalho no divCabecalho
          divCabecalho.appendChild(tabCabecalho);
          
          //Recuperando o cabe�alho da tabela
          var cabecalho;
          for(var x=0; x<tabelaConteudo.childNodes.length;x++){
              //cabecalho = //tabelaConteudo[0].style.display="none";
              cabecalho = tabelaConteudo.childNodes[x];
              if(cabecalho.tagName == "THEAD")
                  break;
          }
          //Escondendo o cabe�alho
          //cabecalho.style.display="none";
          cabecalho.style.visibility = "hidden";
          
          //pula mais uma linha para corresponder � adi��o da tabelaCabecalho
          i++;
      } //fim do else   
  } //fim do for
  if(!browser.isIE)
      adaptaDiv();
          
} //fim 

/***********************************************************************************************************
* Function:  exibeJanelaModal
*     Exibe uma div em uma janela modal. Esta janela ir� sobrepor a janela principal, sendo modal impedir� que qualquer a��o seja 
* executada na principal. Visualmente, escurece a tela e exibe informa��es ao usu�rio dentro de uma div. Esconde tamb�m
* quaisquer mensagens (erro, informa��o, confirma��o) na janela principal, deixando vis�vel somente mensagens dentro da div.
*
* Autor:
*           Dr�usio (equipe do SESAD) em 13/05/2008
*
*           Fl�vio N�brega e Farley Cruz dos Santos (estagi�rios SEQUAS) em 19/05/2008 (melhorias)
*           Fernando Mendon�a Maranho (Sesec) em 28/01/2009 (modifica��es e corre��es)
*
* Par�metros: 
*         
*         idDiv                   - id da div
*         altura                  - altura (em %) da janela modal
*         largura                 - largura (em %) da janela modal
*         posicaoVertical         - define a posi��o vertical da janela modal. Pode assumir os valores "superior" e "inferior". Valores 
* diferentes destes far�o que a janela ser exibida no centro da tela.
*
* Exemplos:
*           ><body onoad="exibeJanelaModal()"/>
*             >adicionaEventoOnload(janelaModal);
*
************************************************************************************************************/
function exibeJanelaModal(idDiv,altura,largura,posicaoVertical){
    
    var ALTURA_MAXIMA_PERMITIDA  = 90; // 90% da altura da �rea de visualiza��o do browser
    var ALTURA_MINIMA_PERMITIDA  = 10; //  10% da altura da �rea de visualiza��o do browser
    
    var LARGURA_MAXIMA_PERMITIDA = 90; // 90% da largura da �rea de visualiza��o do browser
    var LARGURA_MINIMA_PERMITIDA  = 10; // 10% da largura da �rea de visualiza��o do browser
    
    // caso n�o seja especificado corretamente os par�metros
    var ALTURA_PADRAO  = 50; // 50% da altura da �rea de visualiza��o do browser
    var LARGURA_PADRAO = 50; // 50% da largura da �rea de visualiza��o do browser
    
    // recuperando a div que ser� a janela modal
    var div = document.getElementById(idDiv);
    
    //Formatando e mostrando a div
    if(div){
          if(browser.isIE){//Esconde as combos no IE
                selects = document.getElementsByTagName("select");
                for(aux = 0;aux < selects.length; aux++){
                      selects[aux].style.visibility="hidden";
                }
                selects = div.getElementsByTagName("select");
                for(aux = 0;aux < selects.length; aux++){
                      selects[aux].style.visibility="visible";
                }
          }
          // Escurecendo o fundo da tela
          frame = document.getElementById('telaEspera');
          frame.style.display = "";
          frame.style.zIndex = "3";
          
          // Coloca a div acima do fundo escurecido
          div.style.zIndex = "4";
          
          // Valida��es da altura
          if (altura == null || altura.toUpperCase() == "" || altura.toUpperCase() == "AUTO") {
                altura = ALTURA_PADRAO;
          }
          if (altura < ALTURA_MINIMA_PERMITIDA) { 
                altura = ALTURA_MINIMA_PERMITIDA; 
          }
          if (altura > ALTURA_MAXIMA_PERMITIDA) { 
                altura = ALTURA_MAXIMA_PERMITIDA; 
          }
          
          // Valida��es da largura
          if (largura == null || largura.toUpperCase() == "" || largura.toUpperCase() == "AUTO") {
                largura = LARGURA_PADRAO;
          }
          if (largura < LARGURA_MINIMA_PERMITIDA) { 
                largura = LARGURA_MINIMA_PERMITIDA; 
          }
          if (largura > LARGURA_MAXIMA_PERMITIDA) { 
                largura = LARGURA_MAXIMA_PERMITIDA; 
          }
          
          // Define a altura e largura da div
          div.style.height=altura+"%";
          div.style.width=largura+"%";
          
          div.style.marginLeft="0px";
          div.style.marginTop="0px";
          
          
          // define o posicionamento vertical da div
          if(posicaoVertical.toUpperCase() == 'SUPERIOR'){
                margem_superior = (100 - altura) * 1/3; // a margem inferior, portanto, ser� de 2/3 do espa�o escurecido
                div.style.top= margem_superior + '%';    
          } else if(posicaoVertical.toUpperCase() == 'INFERIOR'){
                margem_inferior = (100 - altura) * 1/3; // a margem superior, portanto, ser� de 2/3 do espa�o escurecido
                div.style.bottom= margem_inferior + '%';
                div.style.marginBottom="0px";
          } else{
                margem_superior = (100 - altura) * 1/2;
                div.style.top= margem_superior + '%';
          }
          
          // define o posicionamento horizontal da div (centralizado)
          margem_esquerda = (100 - largura) * 1/2;
          div.style.left = margem_esquerda + '%';
           
          // Escondendo as tabelas de mensagens padr�o
          tabelas = document.getElementsByTagName('TABLE');
          for(i=0;i<tabelas.length;i++){
                if(
                tabelas[i].className.indexOf('msgAtencao')!=-1 ||
                tabelas[i].className.indexOf('msgConfirmacao')!=-1 ||
                tabelas[i].className.indexOf('msgErro')!=-1
                ){
                      tabelas[i].style.display='none';   
                }
          }
          
          //Exibindo as tabelas de mensagens padr�o somente para a div
          tabelasDiv = div.getElementsByTagName('TABLE');
          for(i=0;i<tabelasDiv.length;i++){
                if((tabelasDiv[i].className.indexOf('msgAtencao')!=-1 ||
                tabelasDiv[i].className.indexOf('msgConfirmacao')!=-1 ||
                tabelasDiv[i].className.indexOf('msgErro')!=-1 ) &&
                tabelasDiv[i].title.indexOf('msgErroFixa')==-1
                
                ){
                      tabelasDiv[i].style.display='';
                }
          }
    }     
}


/***********************************************************************************************************
* Function: escondeJanelaModal
*    Fecha a janela modal criada pela fun��o <exibeJanelaModal>. Esta fun��o deve receber como par�metro o id da div 
*    criada para exibir informa��es ao usu�rio.
*
* Autor:
*
*	      Fl�vio N�brega e Farley Cruz dos Santos (estagi�rios SEQUAS) em 11/06/2008
*
* Par�metros: 
*	    
*	    idDiv  - id da div utilizada como janela modal;
*
* Exemplo:
*		><input type="button" value="Fechar" onclick="escondeJanelaModal('modal');"/>
*
************************************************************************************************************/
function escondeJanelaModal(idDiv){

	var div = document.getElementById(idDiv);
	var div_tela_espera = document.getElementById('telaEspera');
	
	if(div && div_tela_espera){	
		if(div.style.display != 'none'){
			div.style.display = 'none';
		}
		
		if(div_tela_espera.style.display != 'none'){
			div_tela_espera.style.display = 'none';
		}
	}
}


/***********************************************************************************************************
* Function:       SetChecked
*      Seleciona ou desseleciona uma ou mais checkboxes atrav�s do nome.
*
* Autor:
*	
*	      C�ssimo em 23/09/2002
*
* Par�metros:
*	 
*			  formulario  - o objeto formul�rio (document.nomedoform)
*			  check       - o nome da checkbox a ser selecionada ou desselecionada
*			  val		  - valor que define se a checkbox ser� selecionada ou desselecionada 
*			                     (0-desselecionado, 1-selecionado)
************************************************************************************************************/

function SetChecked(formulario,check,val){
var i = 0;
for (i=0; i <  formulario.elements.length; i++)
{
 if (formulario.elements[i].name == check){
	 formulario.elements[i].checked = val;
 }
}
}


/***********************************************************************************************************
* Function:       SetCheckedId
*      Seleciona/desseleciona uma ou mais checkboxes atrav�s do id.
*
* Autor:
*	
*	      C�ssimo em 23/09/2002
*
* Par�metros:
*	 
*			  formulario  - o objeto formul�rio (document.nomedoform)
*			  check       - o id da checkbox a ser selecionado ou desselecionado
*			  val		  - valor que define se o checkbox ser� selecionado ou desselecionado 
*			                     (0-desselecionado, 1-selecionado)
************************************************************************************************************/

function SetCheckedId(formulario,check,val){ 
var i = 0;
for (i=0; i <  formulario.elements.length; i++)
{
 if (formulario.elements[i].id == check){
	 formulario.elements[i].checked = val;
 }
}
}


/***********************************************************************************************************
* Function:       setUnset
*      Alterna a sele��o entre as checkboxes. As que est�o marcadas tornam-se desmarcadas e as 
*             desmarcadas tornam-se marcadas. Funciona somente em prot�tipos(HTML).
*
* Autor:	Gustavo - alterada em 11/06/2007 para funcionar em JSF
*	
* Par�metros: 
*	status
*		  
* Exemplo:
*	<input type="checkbox" onClick="setUnset(this)">
************************************************************************************************************/  

function setUnset(status) {
  if (status.checked) {
	  set();	  
	}
	else {
	  unset();	  
	}      
}

/***********************************************************************************************************
* Function:       marcaTodasLinhasTabelaJsf
*     Alterna a sele��o de todas as checkboxes de uma tablela JSF (dataTable). Destaca as linhas selecionadas
* atrav�s de uma chamada interna � fun��o <swapColorJSF>. A chamada deve ser feita em um checkbox dentro de uma
* dataTable.
*
* Autor:
*		 Farley Cruz dos Santos (estagi�rio SEQUAS) em 29/05/2008
*
* Param�tros: 
*			  checkbox - checkbox
*
* Exemplo:
*
* >	<h:dataTable value="#{actUsuario.listaUsuarios}" var="usuario">
* >		<h:column id="column1">
* >			<f:facet name="header">
* >				<h:selectBooleanCheckbox onclick="marcaTodasLinhasTabelaJSF(this);"/>
* >			</f:facet>
* >			<h:selectBooleanCheckbox onclick="swapColorJSF(this)"/>
* >		</h:column>
* >		<h:column id="column2">
* >			<f:facet name="header">
* >				<h:outputText value="Nome"></h:outputText>
* >			</f:facet>
* >			<h:outputText value="#{usuario.nome}"/>
* >		</h:column>
* >	</h:dataTable>
*
* Observa��o: 
*		Ao contr�rio da fun��o <setUnset>, esta fun��o alterna a sele��o das checkboxes somente da tabela
*	em que o checkbox pertence.	
************************************************************************************************************/   

function marcaTodasLinhasTabelaJsf(checkbox){
	tabela = checkbox;
	while(tabela.tagName.toUpperCase()!="TABLE"){
		tabela = tabela.parentNode;
	}
	checks = tabela.getElementsByTagName("INPUT");
	if(checks.length==0){
		checks = tabela.getElementsByTagName("input");
	}
	for(i=0;i<checks.length;i++){
		if(checks[i].type.toUpperCase()=="CHECKBOX" && checks[i]!=checkbox){
			checks[i].checked = checkbox.checked;
			swapColor(checks[i]);
		}
	}
}

	

/***********************************************************************************************************
* Function:       marcaTodasLinhasTabela
*     Alterna a sele��o de todas as checkboxes de uma tablela. Destaca as linhas selecionadas
* atrav�s de uma chamada interna � fun��o <swapColor>. A chamada deve ser feita em um checkbox dentro de uma
* tabelaable.
*
* Autor:
*		 Farley Cruz dos Santos (estagi�rio SEQUAS) em 29/05/2008
*
* Param�tros: 
*			  checkbox - checkbox
*
* Exemplo:
*
* >	<table>
* >		<thead>
* >			<tr>
* >				<th>
* >					<input type="checkbox" onclick="marcaTodasLinhasTabela(this);"/>
* >				</th>
* >				<th>
* >					Nome
* >				</th>
* >			</tr>
* >		</thead>
* >		<tr>
* >			<th>
* >				<input type="checkbox"/>
* >			</th>
* >			<th>
* >				...
* >			</th>
* >		</tr>
* >	</table>
*
* Observa��o: 
*		Ao contr�rio da fun��o <setUnset>, esta fun��o alterna a sele��o das checkboxes somente da tabela
*	em que o checkbox pertence.	
************************************************************************************************************/   

function marcaTodasLinhasTabela(checkbox){
	tabela = checkbox;
	while(tabela.tagName.toUpperCase()!="TABLE"){
		tabela = tabela.parentNode;
	}
	checks = tabela.getElementsByTagName("INPUT");
	if(checks.length==0){
		checks = tabela.getElementsByTagName("input");
	}
	for(i=0;i<checks.length;i++){
		if(checks[i].type.toUpperCase()=="CHECKBOX" && checks[i]!=checkbox){
			checks[i].checked = checkbox.checked;
			swapColor(checks[i]);
		}
	}
}


/***********************************************************************************************************
Function: controlaAlteracaoCheckbox(object)
		Esta fun��o tem como objetivo registrar altera��es efetuadas em checkboxes inicialmente configurados.
		Para utilizar esta fun��o, deve-se criar duas estruturas do tipo <select></select> configuradas da seguinte forma:
			
			1� Select:				
				id = idSelectInserir
				Exemplo: <select id="idSelectInserir" name="checkInserir">
						 </select>
			
		 	2� Select:
				id = idSelectApagar
				Exemplo: <select id="idSelectApagar" name="checkApagar">
						 </select>			
	
	Autor: Fl�vio N�brega estagi�rio da Sequas em 21/08/2008;
	
	Par�metros:
		object: checkbox que ser� utilizado;
		
	Exemplo:
		<input type="checkbox" id="idFilho1" name="idFilho2" onclick="registraAlteracao(this)" />
		
	
************************************************************************************************************/
function controlaAlteracaoCheckbox(object){
	
	var tamanho=0, i=0, f=0,flag=false, flag_repetido = false;
	selects = document.getElementsByTagName('select');
	tamanho = selects.length;

	if(object.checked){
		for(i=0;i<tamanho;i++){
			if(selects[i].id == 'idSelectInserir'){
				for(f=0;f<selects[i].length;f++){
					if(selects[i].options[f].getAttribute('value') == object.id){
						flag_repetido = true;
						return;
					}
				}
			}
		}

		for(i=0;i<tamanho;i++){
			if(selects[i].id == 'idSelectApagar'){
				for(f=0;f<selects[i].length;f++){
					if(selects[i].options[f].getAttribute('value') == object.id){
						selects[i].remove(f);
						flag = true;
						return;
					}
				}
			}
		}
		
		if(flag != true){
			 for(i=0;i<tamanho;i++){
			 	if(selects[i].id == 'idSelectInserir'){		 			
				 		var opt = document.createElement('option');
				 		var no = document.createTextNode(object.name);
				 		insereSelectInserir(object, selects[i], opt, no);
				 		return;
				}
			 }
		}
	}else{		
		for(i=0;i<tamanho;i++){
			if(selects[i].id == 'idSelectApagar'){
				for(f=0;f<selects[i].length;f++){
					if(selects[i].options[f].getAttribute('value') == object.id){
						flag_repetido = true;
						return;
					}
				}
			}
		}
		for(i=0;i<tamanho;i++){
			if(selects[i].id == 'idSelectInserir'){
				for(f=0;f<selects[i].length;f++){
					if(selects[i].options[f].getAttribute('value') == object.id){
						selects[i].remove(f);
						flag = true;
						return;
					}
				}
			}
		}
		if(flag != true){
			 for(i=0;i<tamanho;i++){
			 	if(selects[i].id == 'idSelectApagar'){
			 		var opt = document.createElement('option');
			 		var no = document.createTextNode(object.name);
			 		insereSelectApagar(object, selects[i], opt, no);
			 		return;
				}
			 }
		}
	}
}


function insereSelectInserir(check, select, opt, no){
		opt.appendChild(no);
		opt.setAttribute('value', check.id);
		select.appendChild(opt);
}

function insereSelectApagar(check, select, opt, no){
		opt.appendChild(no);
		opt.setAttribute('value', check.id);
		select.appendChild(opt);
}

//Group: Funcionalidades de 'combobox'

/***********************************************************************************************************
* Function:       SelecionarLinhaCombo
*      Seleciona a linha da combobox que tenha o valor (propriedade "value" do select) passado como par�metro.
*
* Autor:
*	
*	      -
*
* Par�metros:
*	 
*			  combo - combobox a ser tratada
*			  valor - valor da linha a ser selecionada
************************************************************************************************************/

function SelecionarLinhaCombo(Combo,Valor)
{
for (i=0 ; i < Combo.length ; i++) 
  if (Combo(i).value == Valor) {
      Combo.selectedIndex = i ;
      break ;
      }

return i;
}

/***********************************************************************************************************
* Function:       SelecionarTodasLinhas
*      Seleciona todas as linhas de uma combobox.
*
* Autor:
*	
*	      -
*
* Par�metros:
*	 
*			  combo - combobox a ser tratada
************************************************************************************************************/

function SelecionarTodasLinhas(Combo)
{
for (i=0 ; i < Combo.length ; i++) 
  Combo.options[i].selected = true ;

}



/***********************************************************************************************************
* Function:       RetirarLinhaCombo
*      Retira, de uma combo box, a linha que est� selecionada.
*
* Autor:
*	
*	      -
*
* Par�metros:
*	 
*			  combo - combobox a ser tratada
************************************************************************************************************/

function RetirarLinhaCombo(Combo)
{
Label = new Array(0);
Valor = new Array(0);

//verifica se exite alguem selecionado
if (Combo.selectedIndex != -1) {
 // salva campos e valores em array, exceto o que foi selecionado
 for (i=0 , j=0; i < Combo.length ; i++) 
     if (i != Combo.selectedIndex) {
        j++ ;
        Label.length = j ;
        Valor.length = j ;
        Label[j-1] = Combo(i).text ;
        Valor[j-1] = Combo(i).value ;
        }

 // inclui campos, exceto o que foi exclu�do
 Combo.length = 0 ;
 for (i=0 ; i < Label.length ; i++) 
      Combo.options[i]= new Option(Label[i],Valor[i]) ;
 }
}


/***********************************************************************************************************
* Function:       IncluirLinhaCombo
*      Inclui uma linha de uma combo box em outra combo box.
*
* Autor:
*	
*	      -
*
* Par�metros:
*	 
*			  ComboOrigem  - combo box de origem
*			  ComboDestino - combo box de destino
************************************************************************************************************/

function IncluirLinhaCombo(ComboOrigem,ComboDestino)
{
//verifica se exite alguem selecionado
if (ComboOrigem.selectedIndex != -1) {
 // verifica se campo ja esta no combo
 for (i=0; i < ComboDestino.length ; i++) 
     if ((ComboOrigem(ComboOrigem.selectedIndex).value) == (ComboDestino(i).value)) {
        return
        }

 // inclui campo no combo
 ComboDestino.options[ComboDestino.length]= 
 new Option(ComboOrigem(ComboOrigem.selectedIndex).text,
            ComboOrigem(ComboOrigem.selectedIndex).value) ;
 }
}


/***********************************************************************************************************
* Function:       IncluirLinhasSelecionadasCombo
*      Inclui as linhas selecionadas de uma combo box em outra combo box.
*
* Autor:
*	
*	      -
*
* Par�metros:
*	 
*			  ComboOrigem  - combo box de origem
*			  ComboDestino - combo box de destino
************************************************************************************************************/

function IncluirLinhasSelecionadasCombo(ComboOrigem,ComboDestino)
{
//verifica se exite alguem selecionado
if (ComboOrigem.selectedIndex != -1) {
 // verifica se campo ja esta no combo
 for (i=0; i < ComboDestino.length ; i++) 
     if ((ComboOrigem(ComboOrigem.selectedIndex).value) == (ComboDestino(i).value)) {
        return
        }

for (i=0 ; i < ComboOrigem.length ; i++) {
      if (ComboOrigem.options[i].selected) {
	   ComboDestino.options[ComboDestino.length]= 
	   new Option(ComboOrigem(i).text,
	              ComboOrigem(i).value) ;
      }
}
}
}


/***********************************************************************************************************
* Function:       RetirarLinhasSelecionadasCombo
*      Retira as linhas selecionadas de uma combo box.
*
* Autor:
*	
*	      -
*
* Par�metros:
*	 
*			  combo - combo box a ser tratada
************************************************************************************************************/

function RetirarLinhasSelecionadasCombo(Combo)
{
Label = new Array(0);
Valor = new Array(0);

//verifica se exite alguem selecionado
if (Combo.selectedIndex != -1) {
 // salva campos e valores em array, exceto o que foi selecionado
 for (i=0 , j=0; i < Combo.length ; i++) 
     if (!Combo.options[i].selected) {
        j++ ;
        Label.length = j ;
        Valor.length = j ;
        Label[j-1] = Combo(i).text ;
        Valor[j-1] = Combo(i).value ;
     }

 // inclui campos, exceto o que foi exclu�do
 Combo.length = 0 ;
 for (i=0 ; i < Label.length ; i++) 
      Combo.options[i]= new Option(Label[i],Valor[i]) ;
 }
}


/***********************************************************************************************************
* Function:       RecuperarValoresCombo
*      Recupera todos os valores de uma combo box
*
* Autor:
*	
*	      -
*
* Par�metros:
*	 
*			  combo    - combo box a ser tratada
*			  variavel - -
************************************************************************************************************/

function RecuperarValoresCombo(Combo,Variavel)
{
	// atribui combo as variaveis
	parametros='' ;
	
	// recupera valores
	for (i=0; i < Combo.length ; i++) {
	    if (i==0) {parametros = parametros + '?'}
	    else      {parametros = parametros + '&'}
	    
	    parametros = parametros + Variavel + '=' + Combo(i).value ;
	    }
	
	return parametros 
}


/***********************************************************************************************************
* Function:       IncluirRegistroCombo
*      Inclui um campo em uma combo box.
*
* Autor:
*	
*	      -
*
* Par�metros:
*	 
*			  ComboOrigem - combo box de origem
*			  Chave       - valor da chave
*			  Registro    - valor do registro
************************************************************************************************************/

function IncluirRegistroCombo(ComboOrigem,Chave,Registro)
{
//verifica se campo ja esta no combo
for (i=0; i < ComboOrigem.length ; i++) 
  if (ComboOrigem(i).value == Chave) {
     return
     }

//inclui campo no combo
ComboOrigem.options[ComboOrigem.length]= 
new Option(Registro,Chave) ;
}


/***********************************************************************************************************
* Function:       copiaCombo
*      Copia o conte�do de uma combobox para outra combobox.
*
* Autor:
*	
*	      -
*
* Par�metros:
*	 
*			  comboOrigem  - combobox de origem
*			  comboDestino - combobox de destino
************************************************************************************************************/

function copiaCombo(comboOrigem,comboDestino)  {
    // esvazia o combo Destino
     comboDestino.length = 0;
    
    // copia as linhas do combo Origem para o combo Destino
     for (i = 0; i < comboOrigem.length; i++) {
	 comboDestino.options[comboDestino.length] = 
	    new Option(comboOrigem(i).text, comboOrigem(i).value);
     }
    // seleciona no combo destino a linha que estava selecionada no combo Origem
     comboDestino.selectedIndex = comboOrigem.selectedIndex;
}


//Group: Funcionalidades de 'tooltip'

/***********************************************************************************************************
* Function:       exibeTooltip
*   Exibe uma div de tooltip para um elemento qualquer. Deve ser utilizada no evento onmouseover (ou similares). � necess�rio
* que seja definida uma div contendo o texto de tooltip para que a mesma funcione corretamente.
*
* Autor:
*	Farley Cruz dos Santos (Estagi�rio SEQUAS)  em 29/04/2008
*
* Parametros: 
*	obj        -  objeto que receber� a tooltip
*	tooltip    -  id da div de tooltip a ser exibida
*
* Exemplo:
*	> <input type='button' value='OK' onmouseover="exibeTooltip(this,'tooltip');"onmouseout="escondeTooltip('tooltip')"/>
*	> <div id='tooltip' class='tooltip'>Tooltip</div>
*
* Observa��o:
*	Deve ser utilizada em conjunto com a fun��o <escondeTooltip>
************************************************************************************************************/



function exibeTooltip(obj,tooltip){
      div = document.getElementById(tooltip);
      coordenadas = recuperaPosicaoElemento(obj);
      div.style.left = (coordenadas.x+10)+'px'; 
      div.style.top = (coordenadas.y+25)+'px';
      div.style.display='block';
}

/***********************************************************************************************************
* Function:     escondeTooltip
*      Esconde uma div de tooltip. Deve ser utilizada no evento onmouseout (ou similares).� necess�rio
* que seja definida uma div contendo o texto de tooltip para que a mesma funcione corretamente.
*
* Autor:
*	      Farley Cruz dos Santos (Estagi�rio SEQUAS)  em 29/04/2008
*
* Par�metros:
*			  tooltip  - id da div de tooltip a ser escondida
*
* Exemplo:
*	> <input type='button' value='OK' onmouseover="exibeTooltip(this,'tooltip');"onmouseout="escondeTooltip('tooltip')"/>
*	> <div id='tooltip' class='tooltip'>Tooltip</div>
*
* Observa��o:
*	Deve ser utilizada em conjunto com a fun��o <exibeTooltip>
************************************************************************************************************/			

function escondeTooltip(tooltip){
      div = document.getElementById(tooltip);
      div.style.display='none';
}


//Objeto para tooltip de abas Tomahawk
function tooltip(aba,tooltip)
{
	this.aba = aba+'_headerCell';
	this.tooltip = tooltip;
}

/***********************************************************************************************************
* Function:     exibeTooltipAbasTomahawk
*      Exibe uma div de tooltip para um conjunto de abas Tomahawk. Esta fun��o deve ser utilizada como event listener
*  para o evento onmouseout (ou similares) e deve ser utilizada em conjunto com a fun��o <escondeTooltipAbasTomahawk>. 
*  Para instru��es de como utiliz�-la, consultar o guia de utiliza��o (p�gina do Sequas).
*
* Autor:
*	      Farley Cruz dos Santos (Estagi�rio SEQUAS)  em 29/04/2008
*
* Par�metros:
*			  e  - evento
*
* Exemplo:
*	> document.all?document.attachEvent('onmouseover',exibeTooltipAbasTomahawk):document.addEventListener('mouseover',exibeTooltipAbasTomahawk,false);
*
* Observa��o:
*	Deve ser utilizada somente para abas do Tomahawk.
************************************************************************************************************/			

function exibeTooltipAbasTomahawk(e){

	var target = window.event ? window.event.srcElement : e ? e.target : null;
	obj=null;
	
	if(target!=null){
		if(target.tagName=='INPUT'){
			target = target.parentNode;
		}
		if(target.getElementsByTagName('INPUT').length!=0){
			for(aux=0;aux<tooltips.length;aux++){
				if(target.id.indexOf(tooltips[aux].aba)!=-1){
					abas = document.getElementsByTagName('TD');
					for(i=0;i<abas.length;i++){
						
						if((abas[i].id.indexOf(tooltips[aux].aba)!=-1))
						{
							obj = abas[i];
							break;
						}
					}
					if(obj){
							obj = obj.getElementsByTagName('INPUT')[0];
							div = document.getElementById(tooltips[aux].tooltip);
							
							coordenadas = recuperaPosicaoElemento(obj);
							div.style.left = (coordenadas.x+10)+'px'; 
							div.style.top = (coordenadas.y+25)+'px';
							div.style.display='block';
						break;
					}
					else{
						break;
					}
				}
			}
		}
	}
}

/***********************************************************************************************************
* Function:     escondeTooltipAbasTomahawk
*      Esconde uma div de tooltip para um conjunto de abas Tomahawk. Esta fun��o deve ser utilizada como event listener
* para o evento onmouseout (ou similares) e deve ser utilizada em conjunto com a fun��o <exibeTooltipAbasTomahawk>.
* Para instru��es de como utiliz�-la, consultar o guia de utiliza��o (p�gina do Sequas).
*
* Autor:
*	      Farley Cruz dos Santos (Estagi�rio SEQUAS)  em 29/04/2008
*
* Par�metros:
*			  e  - evento
*
* Exemplo:
*	> document.all?document.attachEvent('onmouseout',escondeTooltipAbasTomahawk):document.addEventListener('mouseout',escondeTooltipAbasTomahawk,false);
*
* Observa��o:
*	Deve ser utilizada somente para abas do Tomahawk.
************************************************************************************************************/			

function escondeTooltipAbasTomahawk(e){

	var target = window.event ? window.event.srcElement : e ? e.target : null;
	
	for(aux=0;aux<tooltips.length;aux++){
			div = document.getElementById(tooltips[aux].tooltip);
			if(div){
				div.style.left = '0px'; 
				div.style.top = '0px';
				div.style.display='none';
			}
	}
}

/***********************************************************************************************************
* Function:       getTooltipDiv
*     Em DESUSO. Retorna a div do tooltip.
*
* Autor:
*	
*	      -
*
* Par�metros:
*	 nenhum
************************************************************************************************************/

function getTooltipDiv()
{
 if ( document.getElementById )
    return document.getElementById('divPicklistTooltip');
 else if ( document.all )
    return document.all.divPicklistTooltip;
 else return null;
}


/***********************************************************************************************************
* Function:       createTooltipDiv
*      Em DESUSO. Cria uma div de tooltip.
*
* Autor:
*	
*	      -
*
* Par�metros:
*	 
*			  objSel - -
************************************************************************************************************/

function createTooltipDiv(objSel)
{
 if ( !getTooltipDiv() )  // if this is the first time this function is
 {                        // called then create the tooltip text window.
    var tooltipDiv = "<DIV id='divPicklistTooltip' style='position:absolute;top:0px;left:0px;visibility:hidden'></div>";

    if ( document.body.insertAdjacentHTML )
    {
       document.body.insertAdjacentHTML("beforeEnd",tooltipDiv);
    }
    else if ( document.body.innerHTML )
    {
       document.body.innerHTML += tooltipDiv;
    }

    // necessary for netscape (don`t know why)
    setTimeout( "setupPicklist(" + getQualifiedName(objSel) + ")", 1 );

    return true;
 }

 return false;
}


/***********************************************************************************************************
* Function:       showTooltipText
*      Em DESUSO. Mostra o texto de tooltip.
*
* Autor:
*	
*	      -
*
* Par�metros:
*	 
*			  objSel - -
************************************************************************************************************/

function showTooltipText(objSel)
{
 if ( !objSel || objSel.Y==UNDEFINED )  // null or not setup
 {
    return false;
 }

 var divPicklistTooltip = getTooltipDiv();
 var s = divPicklistTooltip.innerHTML = "";

 with ( objSel )
 {
    if ( strKeyInBuf=="" && title.length )
    {
       s = '<FONT color="blue">' + title + '</font>';
    }
    else if ( selectedIndex>=0 && strKeyInBuf==options[selectedIndex].text )  // unique match found
    {
       s = '<B>' + strKeyInBuf + '</b>';
    }
    else
    {
       var c = strKeyInBuf.substring(strKeyInBuf.length-1,strKeyInBuf.length);
       c = ( c == ' ' ) ? '&nbsp;' : '<B>' + c + '</b>';
       s = strKeyInBuf.substring(0,strKeyInBuf.length-1) + c;
    }

    divPicklistTooltip.innerHTML = '<TABLE cellpadding=0 cellspacing=0 style="background-color:INFOBACKGROUND;'
      + 'font:8pt ms sans serif;padding:2px 2px 2px 2px;color:INFOTEXT;border:1px solid INFOTEXT;">'
      + '<TR><TD align="left"><NOBR>'+s+'</nobr></td></tr></table>';

    divPicklistTooltip.style.posLeft = divPicklistTooltip.style.left = X;
    divPicklistTooltip.style.posTop  = divPicklistTooltip.style.top  = Y;
    divPicklistTooltip.style.visibility = "";

 } // end with

 return true;
}


/***********************************************************************************************************
* Function:       hideTooltipText
*      Em DESUSO. Esconde o texto de tooltip.
*
* Autor:
*	
*	      -
*
* Par�metros:
*	 
*			  objSel - -
************************************************************************************************************/

function hideTooltipText(objSel)
{
 if (objSel) objSel.strKeyInBuf = "";
 var divPicklistTooltip = getTooltipDiv();
 return divPicklistTooltip.innerHTML = "";
}


//Group: Funcionalidades de 'picklist'

/***********************************************************************************************************
* Function:       picklistFocusHandler
*      Mostra o texto de tooltip de um objeto quando o mesmo recebe o foco.
*
* Autor:
*	
*	      -
*
* Par�metros:
*	 
*			  e - evento
************************************************************************************************************/

function picklistFocusHandler(e)
{
 var event = e ? e : window.event;  // to handle both NS and IE events
 var objSel = event.target ? event.target : event.srcElement;
 picklistFocusHandler.objSel = objSel;

 // update data elements
 objSel.X = getX(objSel)+2;
 objSel.Y = getY(objSel)-20;
 objSel.strKeyInBuf = "";

 // display the tooltip help (the title attribute by default)
 showTooltipText(objSel);
}


/***********************************************************************************************************
* Function:       picklistBlurHandler
*      Esconde o texto de tooltip de um objeto quando o mesmo perde o foco.
*
* Autor:
*	
*	      -
*
* Par�metros:
*	
*			  e - evento
************************************************************************************************************/

function picklistBlurHandler(e)
{
 var event = e ? e : window.event;  // to handle both NS and IE events
 var objSel = event.target ? event.target : event.srcElement;
 picklistFocusHandler.objSel = null;

 top.status = (objSel.selectedIndex>-1) ? objSel.options[objSel.selectedIndex].text : "";
 hideTooltipText(objSel);

 return true;
}


/***********************************************************************************************************
* Function:       picklistMouseOverHandler
*		  Mostra o texto de tooltip de um objeto quando o mesmo recebe o foco do ponteiro do mouse.
*
* Autor:
*	
*	      -
*
* Par�metros:
*	 
*			  e - evento
************************************************************************************************************/

function picklistMouseOverHandler(e)
{
 var event = e ? e : window.event;  // to handle both NS and IE events
 var objSel = event.target ? event.target : event.srcElement;

 showTooltipText(objSel);

 return true;
}


/***********************************************************************************************************
* Function:       picklistMouseOutHandler
*      Esconde o texto de tooltip de um objeto quando o mesmo perde o foco do ponteiro do mouse.
*
* Autor:
*	
*	      -
*
* Par�metros:
*	
*			  e - evento
************************************************************************************************************/

function picklistMouseOutHandler(e)
{
 var event = e ? e : window.event;  // to handle both NS and IE events
 var objSel = event.target ? event.target : event.srcElement;

 if ( objSel==picklistFocusHandler.objSel || objSel==document.activeElement )
    return true;

 hideTooltipText();

 return true;
}


//Group: Funcionalidades de 'menu �rvore'

/***********************************************************************************************************
* Function:       Toggle
*      Apresenta ou oculta o div que cont�m os sub-itens do item clicado.
*
* Autor:      
*		-
*
* parametros: 
*			  item - id do link
************************************************************************************************************/

function Toggle(item) {
 
 obj		= document.getElementById(item); 		// pega o div
 visible	= (obj.style.display != "none");   		// verifica se o div est� ou n�o vis�vel 
 key		= document.getElementById("x" + item); 	// pega o link
 
 if (visible) 
 {
   // Troca a imagem apresentada ao lado do link clicado (de folder aberto para folder fechado)
	 key.innerHTML=
	 "<FONT FACE='Wingdings' size='1'>&#48;</FONT><b><span class='textoMenuSiga'>" + item + "</span></b>";
	 
	 // oculta o div
	 obj.style.display="none";
 } 
 else
 {
	 // apresenta o div
   obj.style.display="block";

   // Troca a imagem apresentada ao lado do link clicado (de folder fechado para folder aberto)
	 key.innerHTML=
	 "<FONT FACE='Wingdings' size='1'>&#49;</FONT><b><span class='textoMenuSiga'>" +  item + "</span></b>";
 }
}


/***********************************************************************************************************
* Function:       function Expand
*      Torna vis�vel todas as div da p�gina.
*
* Autor:
*      -
*
* Par�metros: nenhum
************************************************************************************************************/

function Expand() {
 divs = document.getElementsByTagName("DIV");
 
 for (i=0; i <divs.length; i++) {
   if (divs[i].id != null)
	 {
		 divs[i].style.display="block";
		 key = document.getElementById("x" + divs[i].id);
 	     key.innerHTML="<FONT FACE='Wingdings' size='1'>&#49;</FONT><b><span class='textoMenuSiga'>" + divs[i].id + "</span></b>";
   }   
 }
}


/***********************************************************************************************************
* Function:       function Collapse
*      Torna oculta todas as div da p�gina.
* 
* Autor:
*      -
*
* Par�metros: nenhum 
************************************************************************************************************/

function Collapse() {
 divs=document.getElementsByTagName("DIV");
 for (i=0;i<divs.length;i++) {
   if (divs[i].id != null)
	 {
		 divs[i].style.display="none";
		 key=document.getElementById("x" + divs[i].id);
 	     key.innerHTML="<FONT FACE='Wingdings' size='1'>&#48;</FONT><b><span class='textoMenuSiga'>" + divs[i].id + "</span></b>";
   }
 }
}


//Group: Funcionalidades de resolu��o

/***********************************************************************************************************
* Function:       dimensoesInternas
*      Recupera as dimens�es internas (�rea �til) de uma janela de browser. Retorna um array de 2 posi��es, com o 
* valor de x na posi��o 0 e o valor de y na posi��o 1.
*
* Autor:
*	
*	      Farley Cruz dos Santos (estagi�rio SEQUAS) em 14/02/2007
*
* Par�metros: 
*		  Nenhum
************************************************************************************************************/

function dimensoesInternas(){
	var x,y;
	if (self.innerHeight) // funciona para todos os browsers, exceto Explorer
	{
		x = self.innerWidth;
		y = self.innerHeight;
	}
	else if (document.documentElement && document.documentElement.clientHeight)
		// Explorer 6 Modo 'Strict'
	{
		x = document.documentElement.clientWidth;
		y = document.documentElement.clientHeight;
	}
	else if (document.body) // funciona para outros Explorers
	{
		x = document.body.clientWidth;
		y = document.body.clientHeight;
	}
	return new Array(x,y);
}

/***********************************************************************************************************
* Function:       dimensoesPagina
*      Recupera as dimens�es da p�gina (dimens�es do elemento body). O valor � dado em pixels (px). Retorna 
* um array de 2 posi��es, com o valor de x na posi��o 0 e o valor de y na posi��o 1. 
*
* Autor:
*	
*	      Farley Cruz dos Santos (estagi�rio SEQUAS) em 14/02/2007
*
* Par�metros: 
*		  Nenhum
************************************************************************************************************/

function dimensoesPagina(){
	var x,y;
	var test1 = document.body.scrollHeight;
	var test2 = document.body.offsetHeight
	if (test1 > test2) // todos menos Explorer Mac
	{
		x = document.body.scrollWidth;
		y = document.body.scrollHeight;
	}
	else // Explorer Mac;
	     //pode funcionar em Explorer 6 Strict, Mozilla e Safari
	{
		x = document.body.offsetWidth;
		y = document.body.offsetHeight;
	}
	return new Array(x,y);
}

/***********************************************************************************************************
* Function:       resolucaoUsuario
*      Recupera a resolu��o da tela. O valor � dado em pixels (px). Retorna um array de 2 posi��es, com o 
* valor de x na posi��o 0 e o valor de y na posi��o 1.
*
* Autor:
*	
*	      Farley Cruz dos Santos (estagi�rio SEQUAS) em 14/02/2007
*
* Par�metros: 
*		  Nenhum
************************************************************************************************************/

function resolucaoUsuario(){
	var x,y;
	x = self.screen.width;
	y = self.screen.height;
	return new Array(x,y);
}

/***********************************************************************************************************
* Function:       posicaoBrowser
*      Recupera as coordenadas do canto superior esquerdo do browser. O valor � dado em pixels (px). Retorna
* um array de 2 posi��es, com o valor de x na posi��o 0 e o valor de y na posi��o 1.
*
* Autor:
*	
*	      Farley Cruz dos Santos (estagi�rio SEQUAS) em 14/02/2007
*
* Par�metros: 
*		  Nenhum
************************************************************************************************************/

function posicaoBrowser(){
	var x,y;
	if(self.screenX) //Firefox
	{
		x = self.screenX;
		y = self.screenY;
	}
	else if(self.screenTop) //Internet Explorer
	{
		x = self.screenLeft;
		y = self.screenTop-93;
	}
	return new Array(x,y);
}




//Funcionalidades da tabela redimension�vel


//window.onresize=adaptaTabela;

//var idDiv, idTabela, idTbody, idTfoot, idDivFoot;



/***********************************************************************************************************
* Function:       recuperaPosicaoElemento
*      Recupera a posi��o x e y de um elemento. O valor � dado em pixels (px). Retorna 
* um objeto com os atributos x e y, referentes � posi��o do elemento. 
*
* Autor:
*	
*	      Farley Cruz dos Santos (estagi�rio SEQUAS) em 02/03/2007
*
* Par�metros: 
*		  elemento - elemento a ser recuperada a posi��o.
*
* Exemplo:
*	   > var pos = recuperaPosicaoElemento(document.getElementById("tabela1"));
*	   > var topoTabela = pos.x;
************************************************************************************************************/

function recuperaPosicaoElemento (elemento) {
	var coords = { x: 0, y: 0 };
	while (elemento) {
		coords.x += elemento.offsetLeft;
		coords.y += elemento.offsetTop;
		elemento = elemento.offsetParent;
	}
	return coords;
}

//Group: Funcionalidades diversas

/***********************************************************************************************************
* Function:       mostrarData
*      Mostra a data corrente. DIASEMANA, DIAM�S de M�S de ANO. 
*		Exemplo - Qui, 21 de Mar�o de 2002
* 
* Autor:
*	
*	      C�ssimo em 23/02/2002
*
* Par�metros:
*	 nenhum 
************************************************************************************************************/

function mostrarData()
{
arrayDiaSemana = new Array (" Domingo"," Segunda"," Ter�a"," Quarta"," Quinta"," Sexta"," S�bado"); 

arrayMes = new Array ("Janeiro","Fevereiro","Mar�o","Abril","Maio","Junho",
                        "Julho","Agosto","Setembro","Outubro","Novembro","Dezembro"); 

hoje = new Date;
document.write(arrayDiaSemana[hoje.getDay()] 
               //+ ((hoje.getDay() >= 1 || hoje.getDay() <= 5) ? "-feira" : "")
               +", "
               + hoje.getDate()
               +" de "
               + arrayMes[hoje.getMonth()] 
               +" de "
               + hoje.getFullYear());
}


/***********************************************************************************************************
* Function:       SaltaCampo
*		  Muda o foco para o pr�ximo campo quando o tamanho m�ximo do campo � atingido.
*
* Autor:
*	
*	      -
*
* Par�metros:
*	 
*			  campo     - campo que est� com o foco
*			  prox      - pr�ximo campo
*			  tammax    - tamanho m�ximo
*			  teclapres - tecla pressionada
************************************************************************************************************/

function SaltaCampo (campo,prox,tammax,teclapres){
	var tecla = teclapres.keyCode;

	vr = campo.value;
	if( tecla == 109 || tecla == 189 || tecla == 188 || tecla == 110 || tecla == 111 || tecla == 223 || tecla == 108 ){
		campo.value = vr.substr( 0, vr.length - 1 ); }
	else{
	 	vr = vr.replace( "-", "" );
	 	vr = vr.replace( "/", "" );
	 	vr = vr.replace( "/", "" );
	 	vr = vr.replace( ",", "" );
	 	vr = vr.replace( ".", "" );
	 	vr = vr.replace( ".", "" );
	 	vr = vr.replace( ".", "" );
	 	vr = vr.replace( ".", "" );
	 	tam = vr.length;	

	 	if (tecla != 0 && tecla != 9 && tecla != 16 )
			if ( tam == tammax )	
				prox.focus() ;	}
}


/***********************************************************************************************************
* Function:       trataEnter
*      Permite que ao clique do <enter> o foco passe para o pr�ximo campo.
* 
* Autor:
*	
*	      -
*
*
* Parametros: 
*			  field  - campo
*			  event  - evento
************************************************************************************************************/

function trataEnter (field, event) { 

 var keyCode = event.keyCode ? event.keyCode : event.which ? event.which : event.charCode;
 var i;
 if (keyCode == 13) 
 {
    for (i = 0; i < field.form.elements.length; i++)
    if (field == field.form.elements[i])
       break;
    i = (i + 1) % field.form.elements.length;
    field.form.elements[i].focus();
    return false;
 }
 else
 return true;
}

/***********************************************************************************************************
* Function:       swapForm
*      Alterna masc�ras de inputs do tipo texto de forma dinamica. 
*               
* Autor:
*	
*	      Gustavo de Carvalho Dantas (estagi�rio SESAD)em 02/03/2007
*
* Par�metros: 
*			  iValue - � o value do elemento por exemplo onclick="swapForm (this.value)"
************************************************************************************************************/  

function swapForm(iValue) {  
  var valor = "consulta:i" + iValue;        
  var objects = document.getElementsByTagName("INPUT"); 
      for (var i=0; i<objects.length; i++) {
		var thisElement = objects[i];                                
		if (thisElement.getAttribute("type") == "text") {
                      if (thisElement.id == valor) 
                          thisElement.style.display="block";
                      else (thisElement.style.display = "none"); 
              }   
      }
}

/***********************************************************************************************************
* Function:       findCheckedRadio
*		  Localiza qual elemento do tipo radio est� selecionado.
*
* Autor:
*	
*	      Gustavo de Carvalho Dantas (estagi�rio SESAD)em 02/03/2007
*
* Par�metros: 
*		  -
************************************************************************************************************/  

function findCheckedRadio ()
{
  var objects = document.getElementsByTagName("INPUT");
      for (var i=0; i<objects.length; i++) {
          var thisElement = objects[i];
          if (thisElement.getAttribute("type") == "radio"){                
              if (thisElement.checked){
                  swapForm (thisElement.value);
              }
          }
      }
      
}

/***********************************************************************************************************
* Function:       Navegador
*      Identifica o navegador em que a p�gina est� aberta. Identifica os navegadores: Internet Explorer
*	   Netscape 6 e Gecko.
*
* Autor:
*	
*	      -
*
* Par�metros: 
*		  -
************************************************************************************************************/

function Navegador()
{

var ua, s, i;

this.isIE    = false;
this.isNS    = false;
this.version = null;

ua = navigator.userAgent;

s = "MSIE";
if ((i = ua.indexOf(s)) >= 0) {
  this.isIE = true;
  this.version = parseFloat(ua.substr(i + s.length));
  return;
}

s = "Netscape6/";
if ((i = ua.indexOf(s)) >= 0) {
  this.isNS = true;
  this.version = parseFloat(ua.substr(i + s.length));
  return;
}

s = "Gecko";
if ((i = ua.indexOf(s)) >= 0) {
  this.isNS = true;
  this.version = 6.1;
  return;
}
}

/***********************************************************************************************************
* Function:       existeClasse
*      Verifica se existe uma classe de estilo com nome 'name'.
*
* Autor:
*	
*	      -
*
* Par�metros:
*	 
*			  el   - elemento
*			  name - nome para pesquisa
************************************************************************************************************/

function existeClasse(el, name)
{
var i, list;

list = el.className.split(" ");
for (i = 0; i < list.length; i++)
  if (list[i] == name)
    return true;
return false;
}


/***********************************************************************************************************
* Function:       mostraAjuda
*      Apresenta o arquivo htm que cont�m o help online da p�gina web.
*
* Autor:
*	
*	      C�ssimus M�ximus em 30/09/2005
*
* Par�metros:
*	 
*			  nome_janela  -  nome da janela de ajuda
************************************************************************************************************/


function mostraAjuda(nome_janela)  {

if (nome_janela == "{TagLinkHelp}"){
  window.alert("Pedimos desculpas.\nInformamos que o arquivo de ajuda est� em constru��o!");
}
else{
  w = open( nome_janela,
            "help",
            "Scrollbars=1,resizable=1,width=640,height=550, status=yes"
            );
  if ( w.opener == null )
     w.opener = self;
     w.focus();
}
}

/***********************************************************************************************************
* Function:       LimpaCampoTextoAoClicar
*      Limpa um campo de texto ao ser selecionado com o cursor do mouse.
*
* Autor:
*	
*	      Hugo Carrilho(estagi�rio SESOC-Kramer) em 31/05/2006
*
* Par�metros:
*	 
*			  nomeCampoTexto - nome do campo de texto
*
* Exemplo:
*	   > <input class=campotexto style="WIDTH: 80px" onfocus=LimpaCampoTextoAoClicar(txtModelo) value="Modelo" name=txtModelo>
************************************************************************************************************/

function LimpaCampoTextoAoClicar(nomeCampoTexto)
{
	nomeCampoTexto.value = "";
}

/***********************************************************************************************************
* Function:       recuperaObjeto
*       Recupera um objeto em um documento. Fun��o necess�ria para o funcionamento da fun��o "validaForm".
*
* Autor:  
*       Dreamweaver, modificado por Farley Cruz dos Santos (estagi�rio SEQUAS) em 20/03/2007
*	
*
* Parametros:  
*       n - id do elemento
*       d - document
*
* Observa��o:
*       Utilizada somente em conjunto com a fun��o "validaForm".
************************************************************************************************************/
function recuperaObjeto(n, d) { 
var p,i,x;  
if(!d) 
  d=document; 
if((p=n.indexOf("?"))>0&&parent.frames.length) {
  d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);
}
if(!(x=d[n])&&d.all) 
  x=d.all[n]; 
for (i=0;!x&&i<d.forms.length;i++) 
  x=d.forms[i][n];
for(i=0;!x&&d.layers&&i<d.layers.length;i++) 
  x=recuperaObjeto(n,d.layers[i].document);
if(!x && d.getElementById) 
  x=d.getElementById(n); 
return x;
}


/***********************************************************************************************************
* Function:       abreJanelaCentralizada
*      Abre uma janela popup centralizada, janela essa com a largura e altura informada, em pixels
*      Retirando  barra de status, de ferramentas, barra de endere�o e menu.
*
* Autor:
*	
*	      Marco Aur�lio Gabardo - 18/04/2007
*
*	      Alterada em 16/05/2007 - acrescentado o par�metro resize, por Marco Aur�lio	
*
*
* Parametros: 
*			  url       - endere�o da janela a ser aberta
*			  w, h    - w = largura e h = altura
*                         resize = 'yes' ou 'no' 
*
* Exemplo:
*	   > onmouseup="abreJanelaCentralizada('Recurso.jsp?processo=#{cbex.numeroProcesso}&recurso=#{recurso.codigoRecurso}', 600, 400,'yes'); return false;"
*
************************************************************************************************************/

function abreJanelaCentralizada(url,  w, h,resize)
{
w += 32;
h += 96;
wleft = (screen.width - w) / 2;
wtop = (screen.height - h) / 2;
var win = window.open(url,
  '',
  'width=' + w + ', height=' + h + ', ' +
  'left=' + wleft + ', top=' + wtop + ', ' +
  'resizable=' + resize + ', ' +
  'location=no, menubar=no, ' +
  'status=no, toolbar=no, scrollbars=yes');
// Just in case width and height are ignored
win.resizeTo(w, h);
// Just in case left and top are ignored
win.moveTo(wleft, wtop);
win.focus();
}

/***********************************************************************************************************
* Function:       abreJanelaCentralizadaIdentificada
*      Abre uma janela popup centralizada com o nome passado (nomeJanela), janela essa com a largura e altura informada, em pixels
*      Retirando  barra de status, de ferramentas, barra de endere�o e menu.
*
* Autor:
*	
*	      Dr�usio - 08/08/2008
*
*
*
* Parametros: 
*			  nomeJanela   - nome da janela a ser aberta
*			  url       - endere�o da janela a ser aberta
*			  w, h    - w = largura e h = altura
*                         	 resize = 'yes' ou 'no' 
*
* Exemplo:
*	   > onmouseup="abreJanelaCentralizadaIdentificada('Recurso.jsp?processo=#{cbex.numeroProcesso}&recurso=#{recurso.codigoRecurso}', 'janelaRecurso', 600, 400,'yes'); return false;"
*
************************************************************************************************************/

function abreJanelaCentralizadaIdentificada(url,nomejanela,w,h,resize)
{
	w += 32;
	h += 96;
	wleft = (screen.width - w) / 2;
	wtop = (screen.height - h) / 2;
	var win = window.open(url,
	nomejanela,
	 'width=' + w + ', height=' + h + ', ' +
	'left=' + wleft + ', top=' + wtop + ', ' +
	'resizable=' + resize + ', ' +
	'location=no, menubar=no, ' +
	 'status=no, toolbar=no, scrollbars=yes');
	// Just in case width and height are ignored
	win.resizeTo(w, h);
	// Just in case left and top are ignored
	win.moveTo(wleft, wtop);
	win.focus();
}


/***********************************************************************************************************
* Function: maximizarJanela
* 	Fun��o para maximizar uma janela do browser. Compat�vel com IE e Firefox
* 
* Autor:
*	Dr�usio em 12/09/2007
*
* Parametros: 
*	-
*
* Exemplo: 
*	body onload="maximizarJanela();" 
************************************************************************************************************/
function maximizarJanela() {
	if (window.screen) {
		var aw = screen.availWidth;
		var ah = screen.availHeight;
		window.moveTo(0, 0);
		window.resizeTo(aw, ah);
	}
}


/***********************************************************************************************************
* Function: setDojoLimit
* 	Fun��o para atribuir o limite de op��es m�ximas a serem relacionadas em todos os Selects da p�gina.
* 
* Autor:
*	Dennys Nadson Yuzuki Batista (estagi�rio SESAD) em 05/06/2007
*
* Par�metro:
*
*	limit - limite de itens a serem relacionados na op��o do select
*
* Obs: 
*
*	Quando o limit � 0(zero) o valor � ilimitado, ou seja faz com que todos as op��es 
*	buscadas sejam relacionadas.
************************************************************************************************************/

function setDojoLimit(limit)
{
	var sels = dojo.widget.byType("Select")
	for(i=0; i < sels.length; i++)
	{
	var thisElement = sels[i];		
 		thisElement.dataProvider.searchLimit= limit;
	}
}

/***********************************************************************************************************
* Function: adicionaEventoOnload
* 	Adiciona uma fun��o � fila de execu��o do evento window.onload. Deve utilizada quando for necess�rio
*   executar mais de uma fun��o no evento window.onload.
* 
* Autor:
*	Farley Cruz dos Santos (estagi�rio SEQUAS) em 08/04/2008
*
* Par�metro:
*
*	funcao - nome da fun��o a ser adicionada na fila de execu��o
*
* Exemplo: 
*       > adicionaEventoOnload(inicializaCalendario);
*       > adicionaEventoOnload(adaptaDiv);
************************************************************************************************************/

function adicionaEventoOnload(funcao){
  if ( typeof window.addEventListener != "undefined" )
      window.addEventListener( "load", funcao, false );
  else if ( typeof window.attachEvent != "undefined" ) {
      window.attachEvent( "onload", funcao );
  }
  else {
      if ( window.onload != null ) {
          var oldOnload = window.onload;
          window.onload = function ( e ) {
              oldOnload( e );
              window[funcao]();
          };
      }
      else
          window.onload = funcao;
  }
}

/***********************************************************************************************************
* Function: navegarEntrePaginas
* 	Fun��o para atribuir o limite de op��es m�ximas a serem relacionadas em todos os Selects da p�gina.
* 
* Autor:
*	Tulio Marinho Guimaraes (estagiario SEQUAS) em 04/04/2008
*
* Par�metro:
*       numero - utilizado para o controle da navegacao
*
* Observa��o: 
*       O numero recebido como parametro � utilizado para controle da navegacao da seguinte forma:
*           - Numeros positivos (de 0 a Inf.): Seta a pagina que sera mostrada na tabela. Ex.: 1 (pagina 1), 2 (pagina 2)
*           - Numeros negativos: os numeros negativos sao utilizados para uma navegacao relativa a pagina atual com segue:
*                -1 - seta a pagina a ser exibida como a pagina atual -1 (pagina anterior)
*                -2 - seta a pagina a ser exibida como a pagina atual +1 (proxima pagina)
*                -3 - seta a pagina a ser exibida como a primeira pagina de todas
*                -4 - seta a pagina a ser exibida como a ultima pagina de todas
************************************************************************************************************/

function navegarEntrePaginas(numero)
{
      var inputs = document.getElementsByTagName("input")
      for (i=0;i<inputs.length;i++) {
          if(inputs[i].id.indexOf("numeroPagina") > 0) {
              nPagina = inputs[i]
              break;
          }
      }
      var form = nPagina.parentNode
      while(form.nodeName.toUpperCase() != "FORM")
          form = form.parentNode
	if(numero > 0) {
          nPagina.value = numero;
      } else {
          paginaAtual = parseInt(nPagina.value,10);
          if(numero == -1)
              nPagina.value = paginaAtual-1
          if(numero == -2)
              nPagina.value = paginaAtual+1
          if(numero == -3)
              nPagina.value = 1
          if(numero == -4)
              nPagina.value = -1
          //Na act, o valor da pagina igual a '-1' deve ser tratado para exibir a ultima pagina disponivel
      }
      form.submit();
}

/***********************************************************************************************************
* Function: exibeQuantidadeCaracteresRestantes
*           Fun��o para contar a quantidade de caracteres digitadas em um campo texto. 
*	  Dado um campo, o tamanho m�ximo do mesmo, e um componente do tipo spam, a fun��o exibe, 
*           a cada caractere digitado a quantidade de caracteres que ainda restam para atingir o tamanho m�ximo. 
*           Ela tamb�m funciona considerando-se o Ctrl+C e o Ctrl+V.
*
* Autor:
*	Fl�vio N�brega Borges da Concei��o (estagi�rio da SEQUAS) em 25/06/08
*
* Par�metros:
*      nomeCampo - nome do campo texto utilizado como entrada de dados
*      sContador - id do spam utilizado para informar o usu�rio o n�mero de caracteres restantes
*      sTamMax - n�mero m�ximo de caracteres poss�veis para digita��o;
*
* Exemplo:
*               <td style="height: 60px" colspan="4" height="60" align="left">
*                 <textarea name="obs" id="Observacao" onkeyup="exibeQuantidadeCaracteresRestantes('Observacao','contador','100');" rows="4" tabindex="8"></textarea>
*                </td>
*
* ************************************************************************************************************/

function exibeQuantidadeCaracteresRestantes(nomeCampo, sContador, sTamMax){
//recupera o id do campo texto:
Campo = document.getElementById(nomeCampo);
//recuper o id do spam:
Display = document.getElementById(sContador);
//Efetua o c�lculo de caracteres do campo texto e adiciona na vari�vel:
Caracteres = sTamMax - Campo.value.length;
//Mostro a quantidade de caracteres atual:
Display.innerHTML = Caracteres;
//Verifico se o n�mero de caracteres ultrapassou o m�ximo permitido:
 if(Campo.value.length >= sTamMax){
    //Retiro do campo texto os caracteres digitados al�m do permitido:
	  Campo.value = Campo.value.substring(0, sTamMax);
	  //Se o tamanho m�ximo foi atingido, seto o valor do spam para 0:
	  Display.innerHTML = 0;
	}
}


/***********************************************************************************************************
* Function:  retornaParaMesa
* 	Efetua o retorno para a p�gina inicial da mesa , atualizando ou carregando um filtro espec�fico.
* 	Deve ser utlizada para que no fechamento da janela da opera��o ass�ncrona o usu�rio seja redirecionado para mesa.
*
* Autor:
* 		Drausio Gomes dos Santos 15/08/2008
* 
* Par�metro:
* 		codFiltro - nome do campo do formul�rio cujo valor � o c�digo do filtro .
* 		clicked - Atrbuto que controlar� se o evento foi disparado de dentro da p�gina.
*
* Exemplo:
* 	Colocar no head da pagina que chama esta fun��o o seguinte c�digo
*		<script type="text/javascript">
*			 var clicked = false
* 			document.onclick = function(){
* 				clicked=true;
* 			}
* 		</script>
* 	
*  Efetuar a chamada da fun��o no evento unonload da tag <BODY>
*
* <body onunload="retornaParaMesa('tramitacao:retMesa', clicked );" >
**********************************************************************************************************/

function retornaParaMesa( codFiltro , clicked ){
  if (document.getElementById(codFiltro).innerHTML != '' & !clicked){
          var caracter = '?';
          var str = opener.location.href ;
          var tokens = new Array();
          var indiceTokens = 0;    
          var fimStr = str.length;
          var pos = 0;
          tokens[0] = str ;
          
          for ( i=0; i<fimStr; i++){
              if(str.charAt(i) == caracter){
                  tokens[indiceTokens] = str.substring(pos,i);
                  pos = i + 1;
                  indiceTokens++;
              }
          }

          opener.location.href = tokens[0] + '?codFiltro=' + document.getElementById(codFiltro).innerHTML ;
           return true;

	}else{
      return true;
  }
}

/***********************************************************************************************************
* Functions:  gravarValoresIniciais, verificarCampo
* 
* Autor:
* 		Fl�vio N�brega - 30/10/2008
* 
* Par�metro:
		idForm: id do formul�rio utillizado para gravar os valores iniciais.
		idTabela: id da tabela utilizada para mostrar a mensagem de alerta.

Defini��o do problema:
		Estas fun��es s�o frutos da demanda 13044 aberta pelo Marco Aur�lio Gabardo. Inicialmente, a p�gina do formul�rio era submetida todas as 
		vezes que um campo do formul�rio fosse alterado, este submit era necess�rio para que a mensagem gerada pelo ContextObject fosse atualizada. 
		A solu��o encontrada foi a seguinte: desenvolver duas fun��es, uma para gravar os valores do formul�rio de uma p�gina quando a mesma foi carregada (gravarValoresIniciais)
		e a outra � respons�vel por verificar cada altera��o de campo do formul�rio (verificarCampo).
		Observa��es importantes:
			1. Todos os elementos do formul�rio precisam de um id;
			2. Adicionar a tabela abaixo no local onde a mensagem deve aparecer na jsp;
				2.1. O atributo class e id da tabela pode ser alterado;
				2.2 A mensagem na segunda coluna da tabela pode ser editada;
				<table class="msgAtencao" id="idTabelaAtencao"> 
					<tr><td width="20" rowspan="1" valign="top"><img src="../../Shared/imagens/imgIconeAtencao.gif"></td>
					<td rowspan="1" valign="top">Existem dados alterados que ainda n�o foram salvos.</td>
					</tr>
				</table>
			3. Adicionar
* Exemplo:
* 	
* 

		
		<div id="idContextMsg">
			  <%=tcu.util.formatHtml.FormatHtmlJsf.retornaErrosContextObject()%>
            <%=tcu.util.formatHtml.FormatHtmlJsf.retornaAlertasContextObject()%>
            <%=tcu.util.formatHtml.FormatHtmlJsf.retornaInfosContextObject()%>
		</div>
*  Efetuar a chamada da fun��o no evento unonload da tag <BODY>
*
**********************************************************************************************************/
var vetorDadosIniciais = new Array();
var vetorAlterados = new Array();
var totalItensAlterados = 0;

function matriz_valoresIniciais(id, valor){
              this.id = id;
              this.valor = valor;
}

function gravaValoresIniciaisFormulario(idForm, idTabela){
              var valorAtributo="", idAtributo="";
              var i=0;
              var controlador = 0;

              var inputs = document.getElementsByTagName('input');
              var form = document.getElementById(idForm);
              var tabela = document.getElementById(idTabela);
              
              if(browser.isIE){
                             tabela.style.display = 'none';
              }else{
                             tabela.style.visibility = 'hidden';
              }

              var totalElementos = form.elements.length;
              
              for(i=0;i<totalElementos;i++){
                             valorAtributo = form.elements[i].value;
                             if(form.elements[i].getAttribute("type") == "checkbox"){
                                             if(form.elements[i].checked){
                                                             valorAtributo = true;
                                             }else{
                                                             valorAtributo = false;
                                             }
                             }
                             idAtributo = form.elements[i].id;
                             vetorDadosIniciais[controlador] = new matriz_valoresIniciais(idAtributo, valorAtributo);
                             controlador++;
              }
}

function sinalizaAlteracaoExibeMsg(object, idForm, idTabela, idDiv){
var vetorAux = new Array();
var i=0;
var form = document.getElementById(idForm);
var tamanho = vetorDadosIniciais.length;
var totalAlterados = 0;
var cont=0;
var div = document.getElementById(idDiv);
	if(browser.isIE){
		div.style.display = 'none';
	}else{
		div.style.visibility = 'hidden';
	}
	for(i=0;i<tamanho;i++){
		vetorAux[i] = form.elements[i].value;
	}

	for(i=0;i<tamanho;i++){
		if((object.getAttribute("type") == "checkbox") && (object.id == vetorDadosIniciais[i].id)){
			if(object.checked){
				if((object.id == vetorDadosIniciais[i].id) && (true != vetorDadosIniciais[i].valor)){
					totalItensAlterados++;
				}else{
					totalItensAlterados--;
				}
			}else{
				if((object.id == vetorDadosIniciais[i].id) && (false != vetorDadosIniciais[i].valor)){
					totalItensAlterados++;
				}else{
					totalItensAlterados--;
				}
			}
		}else{    

		if((object.id == vetorDadosIniciais[i].id) && (vetorAux[i] != vetorDadosIniciais[i].valor)){
			totalItensAlterados++;
		}else if((object.id == vetorDadosIniciais[i].id) && (vetorAux[i] == vetorDadosIniciais[i].valor)){
			totalItensAlterados--;
		}
	}
}

	if(totalItensAlterados > 0){
		mostrarMsg(idTabela, idDiv);
	}else{
		esconderMsg(idTabela, idDiv);
	}

}

function mostrarMsg(idTabela, idDiv){
	var tabela = document.getElementById(idTabela);
	var div = document.getElementById(idDiv);

	if(browser.isIE){
				   tabela.style.display = '';
	div.style.display = 'none';
	}else{
				   tabela.style.visibility = '';
	div.style.visibility = 'hidden';
	}
}

function esconderMsg(idTabela, idDiv){
	var tabela = document.getElementById(idTabela);
	var div = document.getElementById(idDiv);

	if(browser.isIE){

		tabela.style.display = 'none';
		div.style.display = 'none';
	}else{
		tabela.style.visibility = 'hidden';
		div.style.display = 'hidden';
	}
}


