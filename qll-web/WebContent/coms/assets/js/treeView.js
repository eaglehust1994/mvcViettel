/**
 * Created by quangtao on 8/21/17.
 */

(function(l) {
    l.module("angularTreeview", []).directive("treeModel", function($compile) {
        return {
            restrict: "A",
            link: function(a, g, c) {
                var e = c.treeModel,
                    h = c.nodeLabel || "label",
                    k = '<ul>' +
                            '<li>' +
                                '<span>Trang chủ</span>' +
                            '</li>' +
                            '<li data-ng-repeat="node in ' + e + '">' +
                                '<i class="fa fa-chevron-right" aria-hidden="true"></i>' +
                                '<span data-ng-class="node.selected" data-ng-click="selectNodeLabel(node, $event)">{{node.' + h + '}}</span>' +
                            '</li>' +
                        '</ul>';
                e && e.length && (c.angularTreeview ? (a.$watch(e, function(m, b) {
                    g.empty().html($compile(k)(a))
                }, !1), a.selectNodeHead = a.selectNodeHead || function(a, b) {
                        b.stopPropagation && b.stopPropagation();
                        b.preventDefault && b.preventDefault();
                        b.cancelBubble = !0;
                        b.returnValue = !1;
                        a.collapsed = !a.collapsed
                    }, a.selectNodeLabel = a.selectNodeLabel || function(c, b) {
                        b.stopPropagation && b.stopPropagation();
                        b.preventDefault && b.preventDefault();
                        b.cancelBubble = !0;
                        b.returnValue = !1;
                        a.currentNode && a.currentNode.selected && (a.currentNode.selected = void 0);
                        c.selected = "selected";
                        a.currentNode = c
                    }) : g.html($compile(k)(a)))
            }
        }
    })
})(angular);
