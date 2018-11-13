angular.module('MetronicApp').factory(
    'crDistanceUploadMaterialsService', [
        'RestEndpoint',
        'Restangular',
        function(RestEndpoint, Restangular, $kWindow) {

            var doiduongchuadichvucungcap = {
                dichvudemo : demo
            };
            function demo(){
            	console.log("toi da chay dich vu demo");
            	var obj = Restangular.one(RestEndpoint.CAT_EMPLOYEE_SERVICE_Url,3489).get();
            	return obj;
//            	cÂu lệnh này có nghĩ là lấy một đối tượng employee trong bạng csdl có id là 3489
            	
            };
            
            return doiduongchuadichvucungcap;

     
        }
    ]);