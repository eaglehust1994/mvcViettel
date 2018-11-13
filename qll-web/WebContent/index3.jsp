<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"  
    "http://www.w3.org/TR/html4/loose.dtd">
<%
    request.setAttribute("contextPath", request.getContextPath()); 	
%>
<!--[if IE 8]> <html lang="en" class="ie8 no-js" data-ng-app="MetronicApp"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js" data-ng-app="MetronicApp"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en" data-ng-app="MetronicApp">
    <!--<![endif]-->
    <!-- BEGIN HEAD -->

    <head>
        <title>
            Quản Lý Hoàn Kho
        </title>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <meta content="" name="description" />
        <meta content="" name="author" />
        
         <link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/assets/layouts/layout/img/input/navicon.png"  />
        <!-- BEGIN GLOBAL MANDATORY STYLES -->
      <!--   <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,600,700&subset=all" rel="stylesheet" type="text/css" /> -->
        <link href="${pageContext.request.contextPath}/assets/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/assets/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/assets/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/assets/global/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/assets/global/css/toastr.min.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/assets/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css" />
        <!-- END GLOBAL MANDATORY STYLES -->
        <!-- BEGIN DYMANICLY LOADED CSS FILES(all plugin and page related styles must be loaded between GLOBAL and THEME css files ) -->
        <link id="ng_load_plugins_before" />
        <!-- END DYMANICLY LOADED CSS FILES -->
        <!-- BEGIN THEME STYLES -->
        <!-- DOC: To use 'rounded corners' style just load 'components-rounded.css' stylesheet instead of 'components.css' in the below style tag -->   
        <link href="${pageContext.request.contextPath}/assets/global/css/components.min.css" id="style_components" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/assets/global/css/plugins.min.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/assets/layouts/layout/css/layout.min.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/assets/layouts/layout/css/themes/darkblue.min.css" rel="stylesheet" type="text/css" id="style_color" />
        <link href="${pageContext.request.contextPath}/assets/layouts/layout/css/bootstrap.custom.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/assets/layouts/layout/css/custom.min.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/assets/layouts/layout/css/custom.css" rel="stylesheet" type="text/css" />
        <!-- END THEME STYLES -->
        
        <link href="${pageContext.request.contextPath}/assets/global/kendoui/styles/kendo.common-bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="${pageContext.request.contextPath}/assets/global/kendoui/styles/kendo.bootstrap.min.css" rel="stylesheet" type="text/css" />
        
        
        <link href="${pageContext.request.contextPath}/assets/layouts/layout/css/kendo.custom.css" rel="stylesheet" type="text/css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/layouts/layout/css/angular-ui-tab-scroll.css" />
	    
        <link rel="shortcut icon" href="favicon.ico" /> 
     </head>
    <!-- END HEAD -->
    <!-- BEGIN BODY -->
    <!-- DOC: Apply "page-header-fixed-mobile" and "page-footer-fixed-mobile" class to body element to force fixed header or footer in mobile devices -->
    <!-- DOC: Apply "page-sidebar-closed" class to the body and "page-sidebar-menu-closed" class to the sidebar menu element to hide the sidebar by default -->
    <!-- DOC: Apply "page-sidebar-hide" class to the body to make the sidebar completely hidden on toggle -->
    <!-- DOC: Apply "page-sidebar-closed-hide-logo" class to the body element to make the logo hidden on sidebar toggle -->
    <!-- DOC: Apply "page-sidebar-hide" class to body element to completely hide the sidebar on sidebar toggle -->
    <!-- DOC: Apply "page-sidebar-fixed" class to have fixed sidebar -->
    <!-- DOC: Apply "page-footer-fixed" class to the body element to have fixed footer -->
    <!-- DOC: Apply "page-sidebar-reversed" class to put the sidebar on the right side -->
    <!-- DOC: Apply "page-full-width" class to the body element to have full width page without the sidebar menu -->

    <body ng-controller="AppController" class="page-header-fixed page-sidebar-closed-hide-logo page-on-load page-sidebar-fixed" ng-class="{'page-content-white': settings.layout.pageContentWhite,'page-container-bg-solid': settings.layout.pageBodySolid, 'page-sidebar-closed': settings.layout.pageSidebarClosed}">
        <!-- BEGIN PAGE SPINNER -->
        <div ng-spinner-bar class="page-spinner-bar">
            <div class="bounce1"></div>
            <div class="bounce2"></div>
            <div class="bounce3"></div>
        </div>
        <!-- END PAGE SPINNER -->
        <!-- BEGIN HEADER -->
        <div data-ng-include="'${pageContext.request.contextPath}/tpl/header.html'" data-ng-controller="HeaderController" class="page-header navbar navbar-fixed-top"> </div>
        <!-- END HEADER -->
        <div class="clearfix"> </div>
        <!-- BEGIN CONTAINER -->
        <div class="page-container">
            <!-- BEGIN SIDEBAR -->
            <div data-ng-include="'${pageContext.request.contextPath}/tpl/sidebar.html'" data-ng-controller="SidebarController" class="page-sidebar-wrapper"> </div>
            <!-- END SIDEBAR -->
            <!-- BEGIN CONTENT -->
            <div class="page-content-wrapper">
                <div class="page-content">
                    <!-- BEGIN ACTUAL CONTENT -->
                    <div ui-view class="fade-in-up"> </div>
                    <!-- END ACTUAL CONTENT -->
                </div>
            </div>
            <!-- END CONTENT -->
            <!-- BEGIN QUICK SIDEBAR -->
            <a href="javascript:;" class="page-quick-sidebar-toggler">
                <i class="icon-login"></i>
            </a>
            <div data-ng-include="'${pageContext.request.contextPath}/tpl/quick-sidebar.html'" data-ng-controller="QuickSidebarController" class="page-quick-sidebar-wrapper"></div>
            <!-- END QUICK SIDEBAR -->
        </div>
        <!-- END CONTAINER -->
        <!-- BEGIN FOOTER -->
        <div data-ng-include="'${pageContext.request.contextPath}/tpl/footer.html'" data-ng-controller="FooterController" class="page-footer"> </div>
        <!-- END FOOTER -->
        <!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->
        <!-- BEGIN CORE JQUERY PLUGINS -->
        <!--[if lt IE 9]>
	<script src="${pageContext.request.contextPath}/assets/global/plugins/respond.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/global/plugins/excanvas.min.js"></script> 
	<![endif]-->
        <script src="${pageContext.request.contextPath}/assets/global/plugins/jquery.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/assets/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/assets/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/assets/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/assets/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/assets/global/plugins/jquery.cokie.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/assets/global/plugins/uniform/jquery.uniform.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/assets/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
        <!-- END CORE JQUERY PLUGINS -->
        <!-- BEGIN CORE ANGULARJS PLUGINS -->
        <script src="${pageContext.request.contextPath}/assets/global/plugins/angularjs/angular.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/angular-idle.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/assets/global/plugins/angularjs/angular-sanitize.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/assets/global/plugins/angularjs/angular-touch.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/assets/global/plugins/angularjs/angular-cookies.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/assets/global/plugins/angularjs/plugins/angular-ui-router.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/assets/global/plugins/angularjs/plugins/ocLazyLoad.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/assets/global/plugins/angularjs/plugins/ui-bootstrap-tpls.min.js" type="text/javascript"></script>
        <!-- END CORE ANGULARJS PLUGINS -->
        <!-- BEGIN APP LEVEL ANGULARJS SCRIPTS -->
        <script src="${pageContext.request.contextPath}/js/lodash.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/postal.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/angular-gettext.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/translations.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/restangular.min.js" type="text/javascript"></script>
        <script type="text/javascript">
        	 version =new Date().getTime();
        </script>
        <script src="${pageContext.request.contextPath}/js/main.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/xlsx.full.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/directives.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/gridDetail.directive.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/app.constants.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/app.rest.endpoint.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/erp/common/mainTab.controller.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/erp/common/menu.controller.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/erp/common/GeneralPageController.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/erp/common/CommonService.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/erp/common/CommonUtil.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/erp/glfactlist/fact_list.service.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/erp/common/SearchService.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/erp/common/ModalInstanceController.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/erp/common/Tooltip.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/erp/common/angular-disable-all.min.js" type="text/javascript"></script>
        <!-- END APP LEVEL ANGULARJS SCRIPTS -->
        
        
          <!-- BEGIN DIRECTIVE AUTOCOMPLETE AND VALID -->
        <script src="${pageContext.request.contextPath}/qlhc/component/ComboBox.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/qlhc/component/ComboBoxSecond.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/qlhc/component/SearchBox.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/qlhc/component/SearchBoxSecond.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/qlhc/component/SearchBoxMap.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/qlhc/component/SearchBoxAsset.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/qlhc/component/SearchBoxGrid.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/qlhc/component/SearchGridPopup.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/qlhc/component/ValidateEmail.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/qlhc/component/ValidateTextBox.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/qlhc/component/ComboBoxSecondAsset.js" type="text/javascript"></script>
        
        <!-- END DIRECTIVE AUTOCOMPLETE AND VALID -->
        
        <!-- BEGIN APP LEVEL JQUERY SCRIPTS -->
        <script src="${pageContext.request.contextPath}/assets/global/scripts/app.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/assets/layouts/layout/scripts/layout.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/assets/layouts/global/scripts/quick-sidebar.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/assets/layouts/layout/scripts/demo.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/toastr.min.js" type="text/javascript"></script>
        
        
        <script src="${pageContext.request.contextPath}/assets/global/kendoui/js/kendo.all.min.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/assets/global/kendoui/custom/angular-kendo-window.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/app.kendoConfig.js" type="text/javascript"></script>
        <script src="${pageContext.request.contextPath}/js/window.service.js" type="text/javascript"></script>
	    <script src="${pageContext.request.contextPath}/js/angular-ui-tab-scroll.js"></script>
	    <script src="${pageContext.request.contextPath}/js/jszip.min.js"></script>
	    
        <script>
        toastr.options = {
    		  "closeButton": true,
    		  "debug": false,
    		  "newestOnTop": false,
    		  "progressBar": false,
    		  "positionClass": "toast-bottom-right",
    		  "preventDuplicates": false,
    		  "onclick": null,
    		  "showDuration": "300",
    		  "hideDuration": "1000",
    		  "timeOut": "5000",
    		  "extendedTimeOut": "1000",
    		  "showEasing": "swing",
    		  "hideEasing": "linear",
    		  "showMethod": "fadeIn",
    		  "hideMethod": "fadeOut"
    		}
        </script>
        <!-- END APP LEVEL JQUERY SCRIPTS -->
        <!-- END JAVASCRIPTS -->
    </body>
    <!-- END BODY -->
	
</html>