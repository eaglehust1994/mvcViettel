<!-- <form class="form-horizontal row" role="form" name="commonDelete"
	id="commonDelete" kendo-validator="validator"
	k-options="validatorOptions">
	<div class="form-body">
		<p></p>
		<div class="clearfix">
			<div class="form-group col-md-6">
				<label class="col-md-4 control-label" translate>MĂ£ phiáº¿u</label>
				<div class="col-md-8">
					<input class=" form-control width100" type="text" readonly
						ng-model="data.code" />
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="col-md-4 control-label req" translate>LĂ½ do
					há»§y</label>
				<div class="col-md-8">
					<select kendo-drop-down-list class="form-control"
						id="cancelReasonCode" style="width: 100%" name="cancelReasonName"
						ng-model="data.cancelReasonName"
						k-data-source="caller.dataReasonDelete"
						k-data-value-field="'name'" k-data-text-field="'name'" required
						data-required-msg="LĂ½ do há»§y khĂ´ng Ä‘Æ°á»£c Ä‘á»ƒ trá»‘ng??">
													
					</select> <span data-for='cancelReasonName' class='k-invalid-msg'></span>
				</div>
			</div>
		</div>
		<div class="clearfix">
			<div class="form-group col-md-6">
				<label class="col-md-4 control-label" traslate>NgÆ°á»�i há»§y</label>
				<div class="col-md-8">
					<input class="form-control" name="cancelByName" id="cancelByName"
						ng-model="data.cancelByName" >
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="col-md-4 control-label" translate>NgĂ y há»§y</label>
				<div class="col-md-8">
					<input kendo-date-picker class="form-control width100"
						name="cancelDate" ng-model="data.cancelDate" readonly="readonly"
						k-format="'dd/MM/yyyy'" id="cancelDate" />
				</div>
			</div>
			<script>
				$(function() {
					var transDate = $("#cancelDate");
					transDate.kendoMaskedTextBox({
						mask : "00/00/0000"
					});
					transDate.closest(".k-datepicker").add(transDate)
							.removeClass("k-textbox");
				});
			</script>
		</div>
		<div class="clearfix">
			<div class="form-group col-md-12">
				<div class="form-group col-md-12">
					<label class="col-md-2 control-label" translate>Ghi chĂº</label>
					<div class="col-md-10">
						<div class="input-icon right">
							<textarea class="form-control width100" name="cancelDescription"
								id="cancelDescription" type="text"
								ng-model="data.cancelDescription"></textarea>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="clearfix form-actions margin-top">
			<div class="row">
				<div class="col-md-12 text-center">
					<button type="button"
						class="btn red btn-outline padding-search-right ng-scope"
						id="cancel" ng-click="cancel()" translate>Há»§y bá»�</button>
					<button type="button"
						class="btn blue btn-outline padding-search-right ng-scope"
						ng-click="remove()" translate>Ghi láº¡i</button>
				</div>
			</div>
		</div>
	</div>
</form>
<br /> -->


<form class="form-horizontal row" role="form" name="commonDelete"
	id="commonDelete" kendo-validator="validator"
	k-options="validatorOptions">
	<div class="form-body">
		<p></p>
		<div class="clearfix">
			<div class="form-group col-md-6">
				<label class="col-md-4 control-label" translate>Mã yêu cầu</label>
				<div class="col-md-8">
					<input class=" form-control width100" type="text" readonly
						ng-model="data.code" />
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="col-md-4 control-label req" translate>Lý do
					hủy</label>
				<div class="col-md-8">
					<select kendo-drop-down-list class="form-control"
						id="cancelReasonCode" style="width: 100%" name="cancelReasonName"
						ng-model="data.cancelReasonName"
						k-data-source="caller.dataReasonDelete"
						k-data-value-field="'name'" k-data-text-field="'name'" required
						data-required-msg="Lý do hủy không được để trống">
													
					</select> <span data-for='cancelReasonName' class='k-invalid-msg'></span>
				</div>
			</div>
		</div>
		<div class="clearfix">
			<div class="form-group col-md-6">
				<label class="col-md-4 control-label" traslate>Người hủy</label>
				<div class="col-md-8">
					<input class="form-control" name="cancelby" id="cancelby"
						ng-model="data.cancelby" Readonly>
				</div>
			</div>
			<div class="form-group col-md-6">
				<label class="col-md-4 control-label" translate>Ngày hủy</label>
				<div class="col-md-8">
					<input kendo-date-picker class="form-control width100"
						name="cancelDate" ng-model="data.cancelDate" readonly="readonly"
						k-format="'dd/MM/yyyy'" id="cancelDate" />
				</div>
			</div>
			<script>
				$(function() {
					var transDate = $("#cancelDate");
					transDate.kendoMaskedTextBox({
						mask : "00/00/0000"
					});
					transDate.closest(".k-datepicker").add(transDate)
							.removeClass("k-textbox");
				});
			</script>
		</div>
		<div class="clearfix">
			<div class="form-group col-md-12">
											<div class="form-group col-md-12">
												<label class="col-md-2 control-label" translate>Ghi chú</label>
												<div class="col-md-10">
													<textarea class="form-control" name="description" id="description"
													maxlength="500"
													ng-model="data.cancelDescription"></textarea>
										</div>
									</div>
			</div>
		</div>
		<div class="clearfix form-actions margin-top">
			<div class="row">
				<div class="col-md-12 text-center">
					<button type="button"
						class="btn red btn-outline padding-search-right ng-scope"
						id="cancel" ng-click="cancel()" translate>Hủy bỏ</button>
					<button type="button"
						class="btn blue btn-outline padding-search-right ng-scope"
						ng-click="remove()" translate>Ghi lại</button>
				</div>
			</div>
		</div>
	</div>
</form>
<br />

