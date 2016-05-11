$(document).ready(function() {
	
	$.extend( $.fn.dataTableExt.oStdClasses, {
	    "sSortAsc": "header headerSortDown",
	    "sSortDesc": "header headerSortUp",
	    "sSortable": "header"
	} );
	
	/* Table initialisation */
		$('table').dataTable( {
			"sDom": "<'row'<'span8'l><'span8'f>r>t<'row'<'span8'i><'span8'p>>"
		} );
		
		

})
