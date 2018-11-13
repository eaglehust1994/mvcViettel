
	
	Array.prototype.contains = function(obj) {
	    var i = this.length;
	    while (i--) {
	        if (this[i] === obj) {
	            return true;
	        }
	    }
	    return false;
	}
	String.prototype.replaceAll = function(strTarget, strSubString) {
		var strText = this;
		strText = strText.toLowerCase();
		var intIndexOfMatch = strText.indexOf(strTarget);
		while (intIndexOfMatch != -1) {
			strText = strText.replace(strTarget, strSubString)
			intIndexOfMatch = strText.indexOf(strTarget);
		}
		return strText.replace('"', '');
	}
	
	function chkSelectAllBase(flag, grid) {
		var lockedItem = $("#" + grid._cellId.substring(0, grid._cellId.length - 12) + " > .k-grid-content-locked").find("tr");
		if (lockedItem.length === 0) {
			var rowList = grid.lockedTable == undefined ? grid.table.find("tr") : grid.lockedTable.find("tr");
			rowList.each(function(idx, item) {
				var row = $(item);
				var checkbox = $('[name="gridcheckbox"]', row);
				if(flag) {
					checkbox.prop('checked', true);
				} else {
					checkbox.prop('checked', false);
				}
				
		    });
		} else {
			lockedItem.each(function(idx, item) {
				var row = $(item);
				var checkbox = $('[name="gridcheckbox"]', row);
				if(flag) {
					checkbox.prop('checked', true);
				} else {
					checkbox.prop('checked', false);
				}
				
		    });
		}
	}
	
	function downloadFileWithUrl(url){
	    var elem = document.createElement('a');
	    elem.href = url;
	  //  elem.target = 'hiddenIframe';
	    document.body.appendChild(elem);
    	elem.click();
	}
	
	function addVersionToPath(file){
		if(file){
			if(version){				
				return file+"?tsVersion="+version; 
			}
		}
		return file;
	}