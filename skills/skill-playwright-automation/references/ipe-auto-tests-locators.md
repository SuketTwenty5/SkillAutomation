# IPE Auto Tests Locator Catalog

Source: `/Users/suketsuman/Desktop/Golden Dev/twentyfive-regtest/ipe/src/main/java`

Total locators: 709

| Page | Class | Element | Field | Type | XPath |
|---|---|---|---|---|---|
|  | BaseIpePage | PROPOSALS' tab | bidsTab | BaseWebElement | `//span[text()='PROPOSALS']` |
|  | SimpleTree | Cog' menu | cogMenu | CogMenu | `//table[contains(@class, 'x-grid-item-selected')]//div[@class='ibeCallOut']` |
| Bill Of Material page | BillMaterialPage | Bill of Material' table | billMaterialTable | BillMaterialTable | `//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[@class='x-grid-item-container']` |
| Bill Of Material page | BillMaterialPage | Bill Of Material' tree | billMaterialTree | SimpleTree | `//div[contains(@class,'x-tree-view x-fit-item x-tree-view-default x-scroller')]` |
| Bill Of Material page | BillMaterialPage | BillMaterial_TAB | billMaterialTab | BaseWebElement | `//a[@aria-selected='true']//span[text()='CLINs']` |
| Bill Of Material page | BillMaterialPage | Cog' menu | cogMenu | CogMenu | `//table[contains(@class, 'x-grid-item')]//span[@class='x-tree-node-text ']//div[contains(@class, 'ibeCallOut')]` |
| Bill Of Material page | BillMaterialPage | Hamburger' menu | hamburgerMenu | IpeHumburgerTree | `//a[@data-qtip='More']` |
| Bill Of Material page | BillMaterialPage | Run All Parts' button | runAllPartsButton | BaseWebElement | `//span//span[text()='Run - All Parts']` |
| Bill Of Material page | BillMaterialPage | Yes' button | yesButton | BaseWebElement | `//a//span[text()='Yes']` |
| Billing Items page | BillingItemsPage | 0 Allocated' hyperlink | allocated0hyperlink | BaseWebElement | `//a[text()='0% Allocated']` |
| Billing Items page | BillingItemsPage | Bill of Material' table | billMaterialTable | BillMaterialTable | `//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[@class='x-grid-item-container']` |
| Billing Items page | BillingItemsPage | Bill Of Material' tree | billMaterialTree | SimpleTree | `//div[contains(@class,'x-tree-view x-fit-item x-tree-view-default x-scroller')]` |
| Billing Items page | BillingItemsPage | Billing Items Cost Allocation' table | billingItemCostAllocationTable | LaborAmortizationTable | `//*[@role='dialog' and @aria-hidden='false']` |
| Billing Items page | BillingItemsPage | Billing Items Pricing' tab | popUpPricingTab | BaseWebElement | `//*[@role='dialog' and @aria-hidden='false']//a[@aria-hidden='false' and @role='tab']//span[text()='Pricing']` |
| Billing Items page | BillingItemsPage | Billing Items Pricing' table | billingItemPricingTable | BillingItemsPricingTable | `//*[@data-ref='btnInnerEl' and text()='Pricing']/ancestor::*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//*[@class='x-grid-item-container']` |
| Billing Items page | BillingItemsPage | Billing Items' popUp | billingItemPopUp | BaseWebElement | `//*[@role='dialog' and @aria-hidden='false']` |
| Billing Items page | BillingItemsPage | Billing Items' table | billingItemsTable | BillingItemsTable | `//div[@class='x-grid-item-container' and not(contains(@style, 'transform'))]` |
| Billing Items page | BillingItemsPage | BILLING_ITEMS_TAB | billingItemsTab | BaseWebElement | `//a[@aria-selected='true']//span[text()='Billing Items']` |
| Billing Items page | BillingItemsPage | Cancel' button | cancelButton | BaseWebElement | `//*[@role='dialog' and @aria-hidden='false']//span[text()='Cancel']` |
| Billing Items page | BillingItemsPage | Click here to add' link | addLink | BaseWebElement | `//div[@aria-hidden='false' and @role='tabpanel']//a[@data-grigaddlink='true']//*[text()]` |
| Billing Items page | BillingItemsPage | Cog' menu | cogMenu | CogMenu | `//table[contains(@class, 'x-grid-item')]//span[@class='x-tree-node-text ']//div[contains(@class, 'ibeCallOut')]` |
| Billing Items page | BillingItemsPage | Confirm' button | confirmButton | BaseWebElement | `//*[@role='dialog' and @aria-hidden='false']//*[text()='Confirm']` |
| Billing Items page | BillingItemsPage | Contract Lines Allocations' table | contractLinesAllocTable | ContractLinesCostAllocationsTable | `//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-tabpanel-child'))]//div[@class='x-grid-item-container']` |
| Billing Items page | BillingItemsPage | Cost Allocations' tab | costAllocTab | BaseWebElement | `//span[text()='Cost Allocations']` |
| Billing Items page | BillingItemsPage | Cost' Amount | costAmountCellValue | TextElement | `//*[@role='dialog' and @aria-hidden='false']//*[@data-columnid='quoteItemsPriceGridEstimatingSource']//*[text()='Cost']/ancestor::*[@class='  x-grid-row']//*[@data-columnid='quoteItemsPriceGridUnitPrice']//div` |
| Billing Items page | BillingItemsPage | Dialog Pricing' tab | dialogPricingTab | BillingItemsTable | `//*[@role='dialog' and @aria-hidden='false']//*[@role='tab' and @aria-hidden='false']//*[text()='Pricing']` |
| Billing Items page | BillingItemsPage | Discount or Surcharge' Amount | discountAndSurchargeAmountCellValue | TextElement | `//*[@role='dialog' and @aria-hidden='false']//*[@data-columnid='quoteItemsPriceGridEstimatingSource']//*[text()='Discount or Surcharge']/ancestor::*[@class='  x-grid-row']//*[@data-columnid='quoteItemsPriceGridUnitPrice']//div` |
| Billing Items page | BillingItemsPage | Estimated Cost' textBox | estimatedCostTextbox | IpeCustomInputField | `//*[text()='Estimated Cost (from WBS)']/ancestor::label/following-sibling::div//input[@role='textbox']` |
| Billing Items page | BillingItemsPage | Fixed fee' Amount | fixedFeeAmountCellValue | TextElement | `//*[@role='dialog' and @aria-hidden='false']//*[@data-columnid='quoteItemsPriceGridEstimatingSource']//*[text()='Fixed fee']/ancestor::*[@class='  x-grid-row']//*[@data-columnid='quoteItemsPriceGridUnitPrice']//div` |
| Billing Items page | BillingItemsPage | Hamburger' menu | hamburgerMenu | IpeHumburgerTree | `//a[@data-qtip='More']` |
| Billing Items page | BillingItemsPage | List price' Amount | listPriceAmountCellValue | TextElement | `//*[@role='dialog' and @aria-hidden='false']//*[@data-columnid='quoteItemsPriceGridEstimatingSource']//*[text()='List price']/ancestor::*[@class='  x-grid-row']//*[@data-columnid='quoteItemsPriceGridUnitPrice']//div` |
| Billing Items page | BillingItemsPage | Pricing Strategy' field | pricingStrategyField | IpeDropdownField | `(//div//label//span[text()='Pricing Strategy *']//following::input[1])` |
| Billing Items page | BillingItemsPage | Re-price' button | rePriceButton | BaseWebElement | `//span[normalize-space()='Re-price' and @data-ref='btnInnerEl']` |
| Billing Items page | BillingItemsPage | Revenue Recognition Method' field | revenueRecognitionField | ArrowDropdownField | `(//div//label//span[text()='Revenue Recognition Method ']//following::input[1])` |
| Billing Items page | BillingItemsPage | Run All Parts' button | runAllPartsButton | BaseWebElement | `//span//span[text()='Run - All Parts']` |
| Billing Items page | BillingItemsPage | Yes' button | yesButton | BaseWebElement | `//a//span[text()='Yes']` |
| Billing page | BidsBillingPlanPage | Billing Plan' table | billingPlanTable | ContractLinesTable | `//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-box-item'))]//div[@class='x-grid-item-container']` |
| Billing page | BidsBillingPlanPage | BILLING_PLAN | billingPlanTab | BaseWebElement | `//a[@aria-selected='true']//span[contains(text(),'Billing')]` |
| Billing page | BidsBillingPlanPage | Reprice' button | repriceButton | BaseWebElement | `//span[text()='Re-price']` |
| CLINs page | ClinsPage | BOMs imported successfully' message | bOMsImportedSuccessfullyMessage | BaseWebElement | `//*[contains(text(),'Bom')]/ancestor::*[@role='dialog' and @aria-hidden='false']` |
| CLINs page | ClinsPage | Cell dropdown' arrow | cellDropdownArrow | BaseWebElement | `//*[@role='grid' and @aria-hidden='false']//*[@role='gridcell']//*[contains(@class,'x-form-trigger-default')]` |
| CLINs page | ClinsPage | Cell' dropdown | cellDropdown | BaseWebElement | `//*[@role='listbox' and @aria-hidden='false']` |
| CLINs page | ClinsPage | Click here to add' link | addLink | BaseWebElement | `//a[text()='click here to add']` |
| CLINs page | ClinsPage | CLINS_TAB | clinsTab | BaseWebElement | `//a[@aria-selected='true']//span[text()='CLINs']` |
| CLINs page | ClinsPage | CLINs' table | clinsTable | ClinsTable | `//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-container x-tabpanel-child'))]//div[@class='x-grid-item-container']` |
| CLINs page | ClinsPage | Contract Lines Allocations' table | contractLinesAllocTable | ContractLinesCostAllocationsTable | `//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[@class='x-grid-item-container']` |
| CLINs page | ClinsPage | Cost Allocation' popUp | costAllocationPopUp | BaseWebElement | `//*[@role='dialog' and @aria-hidden='false']//*[text()='Create new end item WBS']` |
| CLINs page | ClinsPage | Create New' button | createNewButton | BaseWebElement | `//*[@role='listbox' and @aria-hidden='false']/ancestor::*[contains(@class,'x-boundlist-default')]//*[text()='Create New']` |
| CLINs page | ClinsPage | Gear' icon | gearIcon | BaseWebElement | `//*[@role='toolbar' and @aria-hidden='false']//*[@data-qtip='Settings' and @aria-hidden='false']` |
| CLINs page | ClinsPage | Group Gear' menu | groupGearMenu | IpeCogTree | `//*[@role='toolbar' and @aria-hidden='false']//*[@data-qtip='Settings' and @aria-hidden='false']` |
| CLINs page | ClinsPage | Group Gear' options | groupGearOptions | BaseWebElement | `//*[@role='menu' and @aria-hidden='false']` |
| CLINs page | ClinsPage | Import BOM' check | costBomCheck | BaseWebElement | `//*[@role='row']//*[@data-columnid='itemsTabBomColumn']` |
| CLINs page | ClinsPage | Import BOMs' button | importBOMsButton | BaseWebElement | `//*[@role='dialog' and not(contains(@class,'x-window-ghost')) and @aria-hidden='false']//*[text()='Import BOMs']` |
| CLINs page | ClinsPage | Line Items Details' table | lineItemDetailsTable | ContractLinesCostAllocationsTable | `//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[@class='x-grid-item-container']` |
| CLINs page | ClinsPage | Re-price' button | rePriceButton | BaseWebElement | `//span[normalize-space()='Re-price' and @data-ref='btnInnerEl']` |
| CLINs page | ClinsPage | Regression Finished Goods 1' link | regressionClinsLink | BaseWebElement | `//div[normalize-space() = 'Regression Finished Goods 1']` |
| CLINs page | ClinsPage | Reload' button | reloadButton | BaseWebElement | `//a[@data-qtip='Reload']` |
| CLINs page | ClinsPage | Select Contract Lines to Import' popUp | selectContractLinesToImportPopUp | BaseWebElement | `//*[@role='dialog' and not(contains(@class,'x-window-ghost')) and @aria-hidden='false']//*[text()='Select Contract Lines to Import']` |
| CLINs page | ClinsPage | Top row' checkbox | topRowCheckbox | BaseWebElement | `//*[@role='dialog' and not(contains(@class,'x-window-ghost')) and @aria-hidden='false']//*[@class='x-column-header-checkbox']` |
| CLINs page | ClinsPage | Update Cost & Prices' link | updateCostAndPricesLink | BaseWebElement | `//div[normalize-space()='Update Cost & Prices']` |
| CLINs page | ClinsPage | Wait for Importing BOMs to complete' message | waitForImportingBOMsToCompleteMessage | BaseWebElement | `//*[contains(text(),'SAP')]/ancestor::*[@role='dialog' and @aria-hidden='false']` |
| CLINs page | ClinsPage | Workbench' tab | workbenchTab | BaseWebElement | `//span[text()='Workbench']` |
| Cost Price Analysis Wbs page | CostPriceAnalysisWbs | Analysis' tab | analysisTab | BaseWebElement | `//span[text()='Analysis']` |
| Cost Price Analysis Wbs page | CostPriceAnalysisWbs | Cog Settings' button | cogMenu | WorkbenchDropButton | `//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-tabpanel-child'))]//a[@data-qtip='Settings']` |
| Cost Price Analysis Wbs page | CostPriceAnalysisWbs | WBS_TAB | wbsTab | BaseWebElement | `//a[@aria-selected='true']//span[contains(text(),'WBS')]` |
| Cost Price Analysis Wbs page | CostPriceAnalysisWbs | Workbench' tab | workbenchTab | BaseWebElement | `//span[text()='Workbench' or text()='Analysis']` |
| Costing page | costingPage | Cancel' button | cancelButton | BaseWebElement | `//*[@role='dialog' and @aria-hidden='false']//*[text()='Cancel']/ancestor::a[@aria-hidden='false']` |
| Costing page | costingPage | Clear Dates' button | clearDatesButton | BaseWebElement | `//*[@role='dialog' and @aria-hidden='false']//*[text()='Clear Dates']/ancestor::a[@aria-hidden='false']` |
| Costing page | costingPage | Confirm' button | confirmButton | BaseWebElement | `//*[@role='dialog' and @aria-hidden='false']//*[text()='Confirm']/ancestor::a[@aria-hidden='false']` |
| Costing page | costingPage | Costing' tab | costingTab | BaseWebElement | `//a[@aria-selected='true']//span[contains(text(),'Costing') or contains(text(),'Pricing')]` |
| Costing page | costingPage | Costing' table | costingTable | BillingItemsTable | `//div[@data-testid='costing-table']` |
| Costing page | costingPage | Edit Price Validity' popUp | editPriceValidityPopUp | BaseWebElement | `//*[@role='dialog' and contains(@class,'x-window-default-resizable')]` |
| Costing page | costingPage | Valid From' field | validFromField | IpeField | `//*[@role='dialog' and @aria-hidden='false']//tr//td[1]//*[contains(@name,'iBEDateTime')]` |
| Costing page | costingPage | Valid To' field | validToField | IpeField | `//*[@role='dialog' and @aria-hidden='false']//tr//td[2]//*[contains(@name,'iBEDateTime')]` |
| Create Cost Source History KEY INFO page | KEYINFOpage | Items' tab | itemsTab | BaseWebElement | `//a//span[contains(text(),'Items')]` |
| Create Cost Source History KEY INFO page | KEYINFOpage | KEY INFO' tab | keyInfoTab | BaseWebElement | `//a[@aria-selected='true']//span[contains(text(),'KEY INFO')]` |
| Escalation or Inflation Rates page | SetUpEscalationOrInflationRatesPopUp | ESCALATION_OR_INFLATION_TAB | setupTab | BaseWebElement | `//a[@aria-selected='true']//span[contains(text(),'Escalation or Inflation Rates')]` |
| Estimate Assumptions | EstimatesAssumptions | ASSUMPTIONS_TAB | AssumptionsTab | BaseWebElement | `//a[@aria-selected='true']//span[text()='Assumptions']` |
| Estimate Consolidated BOM | EstimatesConsolidatedBOM | Additional Data' tab | additionalDataTab | BaseWebElement | `//*[@role='dialog' and @aria-hidden='false']//*[@role='tab' and contains(@class,'x-tab-default') and .//span[text()='Additional Data']]` |
| Estimate Consolidated BOM | EstimatesConsolidatedBOM | Consolidated BOM' table | consolidatedBOMTable | CommonDualTable | `(//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[@class='x-grid-item-container'])` |
| Estimate Consolidated BOM | EstimatesConsolidatedBOM | CONSOLIDATED_BOM_TAB | consolidatedBOMTab | MediumWaitElement | `//a[@aria-selected='true']//span[text()='Consolidated BOM']` |
| Estimate Consolidated BOM | EstimatesConsolidatedBOM | Costing Log' tab | costingLogTab | BaseWebElement | `//*[@role='dialog' and @aria-hidden='false']//*[@role='tab' and contains(@class,'x-tab-default') and .//span[text()='Costing Log']]` |
| Estimate Consolidated BOM | EstimatesConsolidatedBOM | Description Menu' options | descriptionMenuOptions | MenuItemField | `//div[@role='menu' and @aria-hidden='false']` |
| Estimate Consolidated BOM | EstimatesConsolidatedBOM | Edit Details' option | editDetailsOption | BaseWebElement | `//div[@role='menu' and @aria-hidden='false']//*[@role='menuitem' and @aria-hidden='false']//*[text()='Edit Details']` |
| Estimate Consolidated BOM | EstimatesConsolidatedBOM | Generate Supplier RFPs' popup | generateSupplierRFPsPopup | BaseWebElement | `//*[contains(text(),'Generate Supplier RFPs')]/ancestor::*[@role='dialog' and @aria-hidden='false']` |
| Estimate Consolidated BOM | EstimatesConsolidatedBOM | History' tab | historyTab | BaseWebElement | `//*[@role='dialog' and @aria-hidden='false']//*[@role='tab' and contains(@class,'x-tab-default') and .//span[text()='History']]` |
| Estimate Consolidated BOM | EstimatesConsolidatedBOM | Payments' tab | paymentsTab | BaseWebElement | `//*[@role='dialog' and @aria-hidden='false']//*[@role='tab' and contains(@class,'x-tab-default') and .//span[text()='Payments']]` |
| Estimate Consolidated BOM | EstimatesConsolidatedBOM | Proposal Requirements' tab | proposalRequirementsTab | BaseWebElement | `//*[@role='dialog' and @aria-hidden='false']//*[@role='tab' and contains(@class,'x-tab-default') and .//span[text()='Proposal Requirements']]` |
| Estimate Consolidated BOM | EstimatesConsolidatedBOM | Purchased Part: Estimate' popup | purchasedPartEstimatePopup | BaseWebElement | `//*[contains(text(),'Purchased Part: Estimate')]/ancestor::*[@role='dialog' and @aria-hidden='false']` |
| Estimate Consolidated BOM | EstimatesConsolidatedBOM | Remarks' tab | remarksTab | BaseWebElement | `//*[@role='dialog' and @aria-hidden='false']//*[@role='tab' and contains(@class,'x-tab-default') and .//span[text()='Remarks']]` |
| Estimate Consolidated BOM | EstimatesConsolidatedBOM | Response Due On' Date | responseDueOnDate | IpeField | `//input[contains(@title,'Expected date')]` |
| Estimate Consolidated BOM | EstimatesConsolidatedBOM | Supplier RFP’s' notification | supplierRFPsNotification | IpeNotification | `//*[contains(text(),'RFP') and @data-ref='innerCt']/ancestor::*[@role='dialog' and @aria-hidden='false']` |
| Estimate Consolidated BOM | EstimatesConsolidatedBOM | Tags' tab | tagsTab | BaseWebElement | `//*[@role='dialog' and @aria-hidden='false']//*[@role='tab' and contains(@class,'x-tab-default') and .//span[text()='Tags']]` |
| Estimate Forecast | EstimatesForecast | FORECAST_TAB | forecastTab | BaseWebElement | `//a[@aria-selected='true']//span[text()='Forecast']` |
| Estimate Hardware Software | EstimatesHardwareSoftware | Click here to add' link | addLink | BaseWebElement | `(//div[contains(@class, 'x-panel x-tabpanel-child')]//a[text()='click here to add'])[last()]` |
| Estimate Hardware Software | EstimatesHardwareSoftware | SERVICE_TAB | hwSwTab | BaseWebElement | `//a[@aria-selected='true']//span[text()='Hardware & Software']` |
| Estimate Hardware Software | EstimatesHardwareSoftware | Service' table | laborTable | ServiceTable | `(//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[@class='x-grid-item-container'])[2]` |
| Estimate Hardware Software | EstimatesHardwareSoftware | View' dropdown | viewDropdown | IpeViewDropdownField | `//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-tabpanel-child'))]//a[contains(@class, 'x-split-button')][@aria-hidden='false']//span[contains(text(), 'View')]` |
| Estimate Labor | EstimatesLabor | Amortization Method' field | amortizationMethodField | IpeDropdownField | `//*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//input[starts-with(@data-componentid, 'iBEComboBox')]` |
| Estimate Labor | EstimatesLabor | Amortization Schedule' table | laborAmortizationTable | LaborAmortizationTable | `(//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[@class='x-grid-item-container'])[2]` |
| Estimate Labor | EstimatesLabor | Change Planning Mode' window | changeWindow | BaseWebElement | `//*[@role='alertdialog' and @aria-hidden='false']//div[contains(@class,'x-window-header')]//div[text()='Change Planning Mode in Other Estimates']` |
| Estimate Labor | EstimatesLabor | Click here to add' link | addLink | BaseWebElement | `//*[@class='x-grid-scrollbar-clipper ']//a/div` |
| Estimate Labor | EstimatesLabor | Close' button | closeButton | BaseWebElement | `//*[@role='dialog' and @aria-hidden='false']//*[text()='Close']` |
| Estimate Labor | EstimatesLabor | Cog Settings' menu | cogMenu | IpeCogTree | `//div[(contains (@class, 'x-fit-item x-panel-default x-grid')) and contains(@aria-hidden,'false')]//span[contains(@class, 'x-fa fa-cog')]` |
| Estimate Labor | EstimatesLabor | Confirm Formula, Define Local Parameters' popUp | confirmFormulaDefineLocalPopUp | BaseWebElement | `//*[@role='dialog' and @aria-hidden='false']//*[text()='Confirm Formula, Define Local Parameters']` |
| Estimate Labor | EstimatesLabor | COPY LABOR' button | copyLaborButton | BaseWebElement | `//*[text()='COPY LABOR']/ancestor::*[@role='button' and @aria-hidden='false']` |
| Estimate Labor | EstimatesLabor | Description Gear' menuItems | descriptionGearMenuItems | MenuItemField | `//*[@role='menu' and @aria-hidden='false' and @aria-expanded='true']` |
| Estimate Labor | EstimatesLabor | Dialog Confirm' button | dialogConfirm | BaseWebElement | `//*[@role='dialog' and @aria-hidden='false']//*[text()='Confirm']` |
| Estimate Labor | EstimatesLabor | Dialog Result' input | dialogResultInput | IpeField | `//*[@role='dialog' and @aria-hidden='false']//*[contains(text(),'Result')]/ancestor::label/following-sibling::*//input[@aria-hidden='false']` |
| Estimate Labor | EstimatesLabor | Distribute Total Effort' field | distributeTotalEffortField | IpeDropdownField | `//*[@role='dialog' and @aria-hidden='false']//label//*[contains(text(),'effort')]/ancestor::td//input[@aria-hidden='false']` |
| Estimate Labor | EstimatesLabor | Download' button | downloadButton | BaseWebElement | `//*[@role='grid' and @aria-hidden='false']//*[@data-qtip='Download' and @aria-hidden='false']` |
| Estimate Labor | EstimatesLabor | Edit Details' menuItem | editDetailsMenuItem | BaseWebElement | `//div[contains(@class, 'x-menu') and @aria-hidden='false']//a[span[text()='Edit Details']]` |
| Estimate Labor | EstimatesLabor | End of Amortization' checkBox | endOfAmortizationCheckBox | IpeCheckBox | `//*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//*[contains(text(),'end') and @data-ref='boxLabelEl']/ancestor::div[@class='x-form-cb-wrap-inner']//span[contains(@class, 'x-form-checkbox')]` |
| Estimate Labor | EstimatesLabor | End of Amortization' datePicker | endOfAmortizationDatePicker | IpeField | `//*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//*[contains(text(),'End') and @data-ref='labelTextEl']/ancestor::div[contains(@class,'iBEDateTimeCls')]//input` |
| Estimate Labor | EstimatesLabor | End of Depreciation' checkBox | endOfDepreciationCheckBox | IpeCheckBox | `//*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//*[contains(text(),'end') and @data-ref='boxLabelEl']/ancestor::div[@class='x-form-cb-wrap-inner']//span[contains(@class, 'x-form-checkbox')]` |
| Estimate Labor | EstimatesLabor | End of Depreciation' datePicker | endOfDepreciationDatePicker | IpeField | `//*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//*[contains(text(),'End') and @data-ref='labelTextEl']/ancestor::div[contains(@class,'iBEDateTimeCls')]//input` |
| Estimate Labor | EstimatesLabor | Escalation and Inflammation Rates' table | escalationAndInflammationRatesTable | EscalationInflationRatesTable | `(//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-window-item x-panel-default'))]//div[@class='x-grid-item-container'])[2]` |
| Estimate Labor | EstimatesLabor | Estimate for Labor' popup | estimateForLaborPopup | BaseWebElement | `//*[contains(text(),'Labor') and contains(text(),'Estimate')]/ancestor::*[@role='dialog' and @aria-hidden='false']//*[@role='tab' and @aria-selected='true']` |
| Estimate Labor | EstimatesLabor | Filter' button | filterButton | BaseWebElement | `//*[@data-qtip='Filters' and @aria-hidden='false']` |
| Estimate Labor | EstimatesLabor | Formula' table | formulaTable | FormulaTable | `//*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel'] and @aria-hidden='false'` |
| Estimate Labor | EstimatesLabor | Labor Amortization' tab | laborAmortizationTab | LongWaitElement | `//*[@role='dialog' and @aria-hidden='false']//*[@role='tab' and @aria-hidden='false']//span[text()='Labor Amortization']` |
| Estimate Labor | EstimatesLabor | Labor Cost' widget | laborCostWidget | TextElement | `//*[@role='tabpanel' and @aria-hidden='false']//*[@data-qtip='Labor cost']` |
| Estimate Labor | EstimatesLabor | Labor Days' widget | laborDaysWidget | TextElement | `//*[@role='tabpanel' and @aria-hidden='false']//*[@data-qtip='Planned total effort in days']` |
| Estimate Labor | EstimatesLabor | Labor Dual' table | laborDualTable | CommonDualTable | `(//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[@class='x-grid-item-container'])[2]` |
| Estimate Labor | EstimatesLabor | Labor Hours' widget | laborHoursWidget | TextElement | `//*[@role='tabpanel' and @aria-hidden='false']//*[@data-qtip='Planned total effort']` |
| Estimate Labor | EstimatesLabor | Labor Revenue' widget | laborRevenueWidget | TextElement | `//*[@role='tabpanel' and @aria-hidden='false']//*[@data-qtip='Labor revenue']` |
| Estimate Labor | EstimatesLabor | LABOR_TAB | costStructureTab | LongWaitElement | `//a[@aria-selected='true']//span[text()='Labor' or text()='Engineering']` |
| Estimate Labor | EstimatesLabor | Labor' table | laborTable | LaborTable | `(//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[@class='x-grid-item-container'])[2]` |
| Estimate Labor | EstimatesLabor | Labor' widget | laborWidget | TextElement | `//*[@role='tabpanel' and @aria-hidden='false']//*[contains(@data-qtip,'Labor')]` |
| Estimate Labor | EstimatesLabor | Learn more' link | learnMoreLink | BaseWebElement | `//a[@class='x-ibeLinksCls ' and text()='Learn more']` |
| Estimate Labor | EstimatesLabor | No records found, click here to add' button | noRecordFind | LongWaitElement | `//*[@data-grigaddlink='true']//div[text()]` |
| Estimate Labor | EstimatesLabor | Opened' menu | openedMenu | MenuItemField | `//*[@data-ref='listEl' and @role='listbox' and @aria-hidden='false']` |
| Estimate Labor | EstimatesLabor | Other' tab | otherTab | BaseWebElement | `//a[@role='tab' and @aria-hidden='false']//span[text()='Other']` |
| Estimate Labor | EstimatesLabor | Other' widget | otherWidget | TextElement | `//*[@role='tabpanel' and @aria-hidden='false']//*[not(contains(@data-qtip,'Travel')) and contains(@data-qtip,'Other')]` |
| Estimate Labor | EstimatesLabor | Refresh' button | refreshButton | BaseWebElement | `//*[@data-ref='btnIconEl' and contains(@class,'fa-sync-alt ')]` |
| Estimate Labor | EstimatesLabor | Regression Normal Labor Fringe Rate' parameter | regressionNormalLaborFringeRate | BaseWebElement | `//*[@role='dialog' and @aria-hidden='false']//*[text()='Regression Normal Labor Fringe Rate']` |
| Estimate Labor | EstimatesLabor | Regression Service Labor Base' parameter | regressionServiceLaborBaseUSDParameter | BaseWebElement | `//*[@role='dialog' and @aria-hidden='false']//*[text()='Regression Service Labor Base (USD)']` |
| Estimate Labor | EstimatesLabor | RIGHT_HEADER_COLUMN_XPATH | rightHeaderXpath | LongWaitElement | `//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child')) and @aria-hidden='false']//*[contains(@class,'x-panel x-grid-inner-normal') and @aria-hidden='false']//div[@aria-hidden='false' and @role='columnheader']` |
| Estimate Labor | EstimatesLabor | RIGHT_TABLE | rightTable | LongWaitElement | `//*[contains(@class,'x-grid-scrollbar-clipper') and not(contains(@class,'x-grid-scrollbar-clipper-locked'))]` |
| Estimate Labor | EstimatesLabor | Start of Amortization' checkBox | startOfAmortizationCheckBox | IpeCheckBox | `//label[@data-ref='boxLabelEl' and contains(text(), 'start')]/preceding-sibling::span` |
| Estimate Labor | EstimatesLabor | Start of Amortization' datePicker | startOfAmortizationDatePicker | IpeField | `//*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//*[(contains(text(),'start') or contains(text(),'Start')) and @data-ref='labelTextEl']/ancestor::div[contains(@class,'iBEDateTimeCls')]//input` |
| Estimate Labor | EstimatesLabor | Start of Depreciation' checkBox | startOfDepreciationCheckBox | IpeCheckBox | `//label[@data-ref='boxLabelEl' and contains(text(), 'start')]/preceding-sibling::span` |
| Estimate Labor | EstimatesLabor | Start of Depreciation' datePicker | startOfDepreciationDatePicker | IpeField | `//*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//*[(contains(text(),'start') or contains(text(),'Start')) and @data-ref='labelTextEl']/ancestor::div[contains(@class,'iBEDateTimeCls')]//input` |
| Estimate Labor | EstimatesLabor | Test WBS 1' menuItem | testWBS1Menu | MenuItemField | `//*[text()='Test WBS 1']/ancestor::li[@role='option']` |
| Estimate Labor | EstimatesLabor | Test WBS 2' menuItem | testWBS2Menu | MenuItemField | `//*[text()='Test WBS 2']/ancestor::li[@role='option']` |
| Estimate Labor | EstimatesLabor | Total Effort' checkBox | totalEffortCheckBox | BaseWebElement | `//*[@role='dialog' and @aria-hidden='false']//label[contains(text(),'Total effort')]/ancestor::td[@class='x-form-radio-group']//input` |
| Estimate Labor | EstimatesLabor | Total Effort' widget | totalEffortWidget | TextElement | `//*[@role='tabpanel' and @aria-hidden='false']//*[@data-qtip='Planned total effort']` |
| Estimate Labor | EstimatesLabor | Total Value' dropdown | totalValueDropdown | IpeDropdownField | `//*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//input[contains(@id, 'iBEAmount') and contains(@class, 'x-form-field') and @aria-invalid='false']` |
| Estimate Labor | EstimatesLabor | Travel' widget | travelWidget | TextElement | `//*[@role='tabpanel' and @aria-hidden='false']//*[contains(@data-qtip,'Travel')]` |
| Estimate Labor | EstimatesLabor | Update Cost' button | updCostButton | BaseWebElement | `(//div[not(contains(@class,'x-hidden-offsets'))]//div[@data-qtip and @titlelink='updateBoeCostsWithFormula'])[last()]` |
| Estimate Labor | EstimatesLabor | Update Cost' link | updateCostLink | BaseWebElement | `//*[@role='dialog' and @aria-hidden='false']//*[contains(text(),'Update Cost')]` |
| Estimate Labor | EstimatesLabor | Update Estimate totals' link | UpdateEstimateTotalsLink | TextElement | `//*[@role='tabpanel' and @aria-hidden='false']//*[@titlelink='updateBoeCostsWithFormula']` |
| Estimate Labor | EstimatesLabor | Upload' button | uploadButton | BaseWebElement | `//*[@role='grid' and @aria-hidden='false']//*[@data-qtip='Upload' and @aria-hidden='false']` |
| Estimate Labor | EstimatesLabor | View' dropdown | viewDropdown | IpeViewDropdownField | `//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-tabpanel-child'))]//a[contains(@class, 'x-split-button')][@aria-hidden='false']//span[contains(text(), 'View')]` |
| Estimate Labor | EstimatesLabor | Yes' button | yesButton | BaseWebElement | `//a//span[text()='Yes']` |
| Estimate Labor Resources | EstimatesLaborResources | Amortization Method' field | amortizationMethodField | IpeDropdownField | `//*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//input[starts-with(@data-componentid, 'iBEComboBox')]` |
| Estimate Labor Resources | EstimatesLaborResources | Amortization Schedule' table | laborAmortizationTable | LaborAmortizationTable | `(//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[@class='x-grid-item-container'])[2]` |
| Estimate Labor Resources | EstimatesLaborResources | Change Planning Mode' window | changeWindow | BaseWebElement | `//*[@role='alertdialog' and @aria-hidden='false']//div[contains(@class,'x-window-header')]//div[text()='Change Planning Mode in Other Estimates']` |
| Estimate Labor Resources | EstimatesLaborResources | Click here to add' link | addLink | BaseWebElement | `//*[@class='x-grid-scrollbar-clipper ']//a/div` |
| Estimate Labor Resources | EstimatesLaborResources | Close' button | closeButton | BaseWebElement | `//*[@role='dialog' and @aria-hidden='false']//*[text()='Close']` |
| Estimate Labor Resources | EstimatesLaborResources | Cog Settings' menu | cogMenu | IpeCogTree | `//div[(contains (@class, 'x-fit-item x-panel-default x-grid')) and contains(@aria-hidden,'false')]//span[contains(@class, 'x-fa fa-cog')]` |
| Estimate Labor Resources | EstimatesLaborResources | Confirm' button | confirmButton | BaseWebElement | `//*[@role='dialog' and @aria-hidden='false']//*[text()='Confirm']` |
| Estimate Labor Resources | EstimatesLaborResources | COPY LABOR' button | copyLaborButton | BaseWebElement | `//*[text()='COPY LABOR']/ancestor::*[@role='button' and @aria-hidden='false']` |
| Estimate Labor Resources | EstimatesLaborResources | Data saved successfully' popUp | dataSavedSuccessfullyPopUp | LongWaitElement | `//*[text()='Data saved successfully']/ancestor::*[@role='dialog' and @aria-hidden='false']` |
| Estimate Labor Resources | EstimatesLaborResources | Description Gear' menuItems | descriptionGearMenuItems | MenuItemField | `//*[@role='menu' and@aria-hidden='false' and @aria-expanded='true']` |
| Estimate Labor Resources | EstimatesLaborResources | Distribute Total Effort' field | distributeTotalEffortField | IpeDropdownField | `//*[@role='dialog' and @aria-hidden='false']//label//*[contains(text(),'effort')]/ancestor::td//input[@aria-hidden='false']` |
| Estimate Labor Resources | EstimatesLaborResources | Download' button | downloadButton | BaseWebElement | `//*[@role='grid' and @aria-hidden='false']//*[@data-qtip='Download' and @aria-hidden='false']` |
| Estimate Labor Resources | EstimatesLaborResources | Edit Details' menuItem | editDetailsMenuItem | BaseWebElement | `//div[contains(@class, 'x-menu') and @aria-hidden='false']//a[span[text()='Edit Details']]` |
| Estimate Labor Resources | EstimatesLaborResources | End of Amortization' checkBox | endOfAmortizationCheckBox | IpeCheckBox | `//*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//*[contains(text(),'end') and @data-ref='boxLabelEl']/ancestor::div[@class='x-form-cb-wrap-inner']//span[contains(@class, 'x-form-checkbox')]` |
| Estimate Labor Resources | EstimatesLaborResources | End of Amortization' datePicker | endOfAmortizationDatePicker | IpeField | `//*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//*[contains(text(),'End') and @data-ref='labelTextEl']/ancestor::div[contains(@class,'iBEDateTimeCls')]//input` |
| Estimate Labor Resources | EstimatesLaborResources | End of Depreciation' checkBox | endOfDepreciationCheckBox | IpeCheckBox | `//*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//*[contains(text(),'end') and @data-ref='boxLabelEl']/ancestor::div[@class='x-form-cb-wrap-inner']//span[contains(@class, 'x-form-checkbox')]` |
| Estimate Labor Resources | EstimatesLaborResources | End of Depreciation' datePicker | endOfDepreciationDatePicker | IpeField | `//*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//*[contains(text(),'End') and @data-ref='labelTextEl']/ancestor::div[contains(@class,'iBEDateTimeCls')]//input` |
| Estimate Labor Resources | EstimatesLaborResources | Estimate for Labor' popup | estimateForLaborPopup | BaseWebElement | `//*[contains(text(),'Estimate for')]/ancestor::*[@role='dialog' and @aria-hidden='false']//*[@role='tab' and @aria-selected='true']` |
| Estimate Labor Resources | EstimatesLaborResources | Filter' button | filterButton | BaseWebElement | `//*[@data-qtip='Filters' and @aria-hidden='false']` |
| Estimate Labor Resources | EstimatesLaborResources | Hamburger' menu | hamburgerMenu | IpeHumburgerTree | `//a[@data-qtip='More']` |
| Estimate Labor Resources | EstimatesLaborResources | Labor Amortization' tab | laborAmortizationTab | LongWaitElement | `//*[@role='dialog' and @aria-hidden='false']//*[@role='tab' and @aria-hidden='false']//span[text()='Labor Amortization']` |
| Estimate Labor Resources | EstimatesLaborResources | Labor Grid' rows | laborGridRows | BaseWebElement | `//*[contains(@class,'x-grid-scroll-container')]//*[contains(@class,'x-grid-scrollbar-clipper')][2]//table[1]//tr//td[1]` |
| Estimate Labor Resources | EstimatesLaborResources | LABOR_TAB | costStructureTab | LongWaitElement | `//a[@aria-selected='true']//span[text()='Labor' or text()='Engineering' or text()='Labor Resources']` |
| Estimate Labor Resources | EstimatesLaborResources | Labor' table | laborTable | LaborTable | `(//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[@class='x-grid-item-container'])[2]` |
| Estimate Labor Resources | EstimatesLaborResources | Learn more' link | learnMoreLink | BaseWebElement | `//a[@class='x-ibeLinksCls ' and text()='Learn more']` |
| Estimate Labor Resources | EstimatesLaborResources | RIGHT_HEADER_COLUMN_XPATH | rightHeaderXpath | LongWaitElement | `//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child')) and @aria-hidden='false']//*[contains(@class,'x-panel x-grid-inner-normal') and @aria-hidden='false']//div[@aria-hidden='false' and @role='columnheader']` |
| Estimate Labor Resources | EstimatesLaborResources | Start of Amortization' checkBox | startOfAmortizationCheckBox | IpeCheckBox | `//label[@data-ref='boxLabelEl' and contains(text(), 'start')]/preceding-sibling::span` |
| Estimate Labor Resources | EstimatesLaborResources | Start of Amortization' datePicker | startOfAmortizationDatePicker | IpeField | `//*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//*[(contains(text(),'start') or contains(text(),'Start')) and @data-ref='labelTextEl']/ancestor::div[contains(@class,'iBEDateTimeCls')]//input` |
| Estimate Labor Resources | EstimatesLaborResources | Start of Depreciation' checkBox | startOfDepreciationCheckBox | IpeCheckBox | `//label[@data-ref='boxLabelEl' and contains(text(), 'start')]/preceding-sibling::span` |
| Estimate Labor Resources | EstimatesLaborResources | Start of Depreciation' datePicker | startOfDepreciationDatePicker | IpeField | `//*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//*[(contains(text(),'start') or contains(text(),'Start')) and @data-ref='labelTextEl']/ancestor::div[contains(@class,'iBEDateTimeCls')]//input` |
| Estimate Labor Resources | EstimatesLaborResources | Total Effort' checkBox | totalEffortCheckBox | BaseWebElement | `//*[@role='dialog' and @aria-hidden='false']//label[contains(text(),'Total effort')]/ancestor::td[@class='x-form-radio-group']//input` |
| Estimate Labor Resources | EstimatesLaborResources | Total Value' dropdown | totalValueDropdown | IpeDropdownField | `//*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//input[contains(@id, 'iBEAmount') and contains(@class, 'x-form-field') and @aria-invalid='false']` |
| Estimate Labor Resources | EstimatesLaborResources | Update Cost' button | updCostButton | BaseWebElement | `//*[@titlelink='updateCostsWithFormula']` |
| Estimate Labor Resources | EstimatesLaborResources | Update Cost' link | updateCostLink | BaseWebElement | `//*[@role='dialog' and @aria-hidden='false']//*[contains(text(),'Update Cost')]` |
| Estimate Labor Resources | EstimatesLaborResources | Upload' button | uploadButton | BaseWebElement | `//*[@role='grid' and @aria-hidden='false']//*[@data-qtip='Upload' and @aria-hidden='false']` |
| Estimate Labor Resources | EstimatesLaborResources | View' dropdown | viewDropdown | IpeViewDropdownField | `//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-tabpanel-child'))]//a[contains(@class, 'x-split-button')][@aria-hidden='false']//span[contains(text(), 'View')]` |
| Estimate Labor Resources | EstimatesLaborResources | Yes' button | yesButton | BaseWebElement | `//a//span[text()='Yes']` |
| Estimate Other | EstimatesOther | Opened' menu | openedMenu | MenuItemField | `//*[@data-ref='listEl' and @role='listbox' and @aria-hidden='false']` |
| Estimate Other | EstimatesOther | OTHER_TAB | costStructureTab | BaseWebElement | `//a[@aria-selected='true']//span[text()='Other']` |
| Estimate Other | EstimatesOther | Other' table | otherTable | CommonDualTable | `(//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[@class='x-grid-item-container'])[2]` |
| Estimate Other | EstimatesOther | Proposal BOM' tab | proposalBOMTab | BaseWebElement | `//a[@role='tab' and @aria-hidden='false']//span[text()='Proposal BOM']` |
| Estimate Procurement & Production | EstimatesProcurementProduction | Amortization Schedule' table | laborAmortizationTable | LaborAmortizationTable | `(//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[@class='x-grid-item-container'])[2]` |
| Estimate Procurement & Production | EstimatesProcurementProduction | Asset Depreciation' tab | assetDepreciationTab | BaseWebElement | `//*[@role='dialog' and @aria-hidden='false']//*[@role='tablist' and @aria-hidden='false']//*[@role='tab' and @aria-hidden='false']//*[text()='Asset Depreciation']/ancestor::*[@role='tab' and @aria-hidden='false']` |
| Estimate Procurement & Production | EstimatesProcurementProduction | Confirm Formula, Define Local Parameters' popUp | confirmFormulaDefineLocalPopUp | BaseWebElement | `//*[@role='dialog' and @aria-hidden='false']//*[text()='Confirm Formula, Define Local Parameters']` |
| Estimate Procurement & Production | EstimatesProcurementProduction | Depreciation Method' field | amortizationMethodField | IpeDropdownField | `//*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//input[starts-with(@data-componentid, 'iBEComboBox')]` |
| Estimate Procurement & Production | EstimatesProcurementProduction | Dialog Confirm' button | dialogConfirm | BaseWebElement | `//*[@role='dialog' and @aria-hidden='false']//*[text()='Confirm']` |
| Estimate Procurement & Production | EstimatesProcurementProduction | Dialog Result' input | dialogResultInput | IpeField | `//*[@role='dialog' and @aria-hidden='false']//*[contains(text(),'Result')]/ancestor::label/following-sibling::*//input[@aria-hidden='false']` |
| Estimate Procurement & Production | EstimatesProcurementProduction | Edit Details' menuItem | editDetailsMenuItem | BaseWebElement | `//div[contains(@class, 'x-menu') and @aria-hidden='false']//a[span[text()='Edit Details']]` |
| Estimate Procurement & Production | EstimatesProcurementProduction | End of Depreciation' checkBox | endOfDepreciationCheckBox | IpeCheckBox | `//*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//*[contains(text(),'end') and @data-ref='boxLabelEl']/ancestor::div[@class='x-form-cb-wrap-inner']//span[contains(@class, 'x-form-checkbox')]` |
| Estimate Procurement & Production | EstimatesProcurementProduction | End of Depreciation' datePicker | endOfDepreciationDatePicker | IpeField | `//*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//*[contains(text(),'End') and @data-ref='labelTextEl']/ancestor::div[contains(@class,'iBEDateTimeCls')]//input` |
| Estimate Procurement & Production | EstimatesProcurementProduction | Estimate for Labor' popup | estimateForLaborPopup | BaseWebElement | `//*[contains(text(),'Estimate for')]/ancestor::*[@role='dialog' and @aria-hidden='false']//*[@role='tab' and @aria-selected='true']` |
| Estimate Procurement & Production | EstimatesProcurementProduction | Material & Equipment Burden Rate' parameter | materialEquipmentBurdenRateParameter | BaseWebElement | `//*[@role='dialog' and @aria-hidden='false']//*[text()='Material & Equipment Burden Rate']` |
| Estimate Procurement & Production | EstimatesProcurementProduction | Mfg' table | mfgTable | CostBreakDownTable | `(//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[@class='x-grid-item-container'])[2]` |
| Estimate Procurement & Production | EstimatesProcurementProduction | Opened' menu | openedMenu | MenuItemField | `//*[@data-ref='listEl' and @role='listbox' and @aria-hidden='false']` |
| Estimate Procurement & Production | EstimatesProcurementProduction | Procurement & Production' tab | proposalBOMTab | BaseWebElement | `//a[@role='tab' and @aria-hidden='false']//span[text()='Procurement & Production']` |
| Estimate Procurement & Production | EstimatesProcurementProduction | Procurement & Production' table | procurementProductionTable | CommonDualTable | `(//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[@class='x-grid-item-container'])[2]` |
| Estimate Procurement & Production | EstimatesProcurementProduction | PROCUREMENT_PRODUCTION_TAB | costStructureTab | BaseWebElement | `//a[@aria-selected='true']//span[text()='Procurement & Production']` |
| Estimate Procurement & Production | EstimatesProcurementProduction | Purchased Parts Base' parameter | purchasedPartsBaseParameter | BaseWebElement | `//*[@role='dialog' and @aria-hidden='false']//*[text()='Purchased Parts Base (USD)']` |
| Estimate Procurement & Production | EstimatesProcurementProduction | Regression - General & Admin Rate' parameter | regressionGeneralAdminRateParameter | BaseWebElement | `//*[@role='dialog' and @aria-hidden='false']//*[text()='Regression - General & Admin Rate']` |
| Estimate Procurement & Production | EstimatesProcurementProduction | Start of Depreciation' checkBox | startOfDepreciationCheckBox | IpeCheckBox | `//label[@data-ref='boxLabelEl' and contains(text(), 'start')]/preceding-sibling::span` |
| Estimate Procurement & Production | EstimatesProcurementProduction | Start of Depreciation' datePicker | startOfDepreciationDatePicker | IpeField | `//*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//*[(contains(text(),'start') or contains(text(),'Start')) and @data-ref='labelTextEl']/ancestor::div[contains(@class,'iBEDateTimeCls')]//input` |
| Estimate Procurement & Production | EstimatesProcurementProduction | Total Value' dropdown | totalValueDropdown | IpeDropdownField | `//*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//input[contains(@id, 'iBEAmount') and contains(@class, 'x-form-field') and @aria-invalid='false']` |
| Estimate Proposal BOM | EstimatesProposalBOM | Additional Data' tab | additionalDataTab | BaseWebElement | `//*[@role='dialog' and @aria-hidden='false']//*[@role='tab' and @aria-hidden='false']//span[text()='Additional Data']` |
| Estimate Proposal BOM | EstimatesProposalBOM | All Parts' menuItem | allPartsMenuItem | BaseWebElement | `//*[@role='menu' and @aria-hidden='false']//*[@role='menuitem' and @aria-hidden='false']//*[contains(text(),'All Parts') or contains(text(),'All parts')]/ancestor::a` |
| Estimate Proposal BOM | EstimatesProposalBOM | Bill of material is being consolidated' notification | consolidatedBOMUpdateNotification | BaseWebElement | `//div[@data-ref='innerCt' and contains(translate(normalize-space(.), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'material is being consolidated')]/ancestor::*[@role='dialog' and @aria-hidden='false']` |
| Estimate Proposal BOM | EstimatesProposalBOM | Consolidate and Cost Bill of Material' headers | consolidateAndCostBillOfMaterialHeader | MenuItemField | `//*[@role='dialog' and not(contains(@class,'x-window-ghost')) and @aria-hidden='false']//*[@role='tab' and @aria-hidden='false']` |
| Estimate Proposal BOM | EstimatesProposalBOM | Consolidate and Cost Bill of Material' popup | consolidateAndCostBillOfMaterialPopup | BaseWebElement | `//*[@role='dialog' and not(contains(@class,'x-window-ghost')) and @aria-hidden='false']//*[text()='Consolidate and Cost Bill of Material']` |
| Estimate Proposal BOM | EstimatesProposalBOM | Costing Log' tab | costingLogTab | BaseWebElement | `//*[@role='dialog' and @aria-hidden='false']//*[@role='tab' and @aria-hidden='false']//span[text()='Costing Log']` |
| Estimate Proposal BOM | EstimatesProposalBOM | Expand All' menuItem | expandAllMenuItem | BaseWebElement | `//*[@role='menu' and @aria-hidden='false']//*[@role='menuitem' and @aria-hidden='false']//*[contains(text(),'Expand All')]/ancestor::a` |
| Estimate Proposal BOM | EstimatesProposalBOM | Expand or Collapse' menuItem | expandOrCollapseMenuItem | BaseWebElement | `//*[@role='menu' and @aria-hidden='false']//*[@role='menuitem' and @aria-hidden='false']//*[contains(text(),'Expand/Collapse')]/ancestor::a` |
| Estimate Proposal BOM | EstimatesProposalBOM | Generate Sourcing Plan' menuItem | generateSourcingPlanMenuItem | BaseWebElement | `//*[@role='menu' and @aria-hidden='false']//*[@role='menuitem' and @aria-hidden='false']//*[contains(text(),'Generate Sourcing Plan')]/ancestor::a` |
| Estimate Proposal BOM | EstimatesProposalBOM | Hamburger' menu options | hamburgerMenuOptions | MenuItemField | `//*[@role='menu' and @aria-hidden='false']//*[@role='menuitem' and @aria-hidden='false']` |
| Estimate Proposal BOM | EstimatesProposalBOM | History' tab | historyTab | BaseWebElement | `//*[@role='dialog' and @aria-hidden='false']//*[@role='tab' and @aria-hidden='false']//span[text()='History']` |
| Estimate Proposal BOM | EstimatesProposalBOM | MFC defaults shared' dropdown | mceDefaultsSharedDropdown | BaseWebElement | `//*[@role='menu' and @aria-hidden='false']//*[@role='menuitem' and @aria-hidden='false']//*[contains(text(),'MFC/M510')]` |
| Estimate Proposal BOM | EstimatesProposalBOM | Part Number Menu' options | partNumberMenuOptions | BaseWebElement | `//*[@role='menu' and @aria-hidden='false']//*[contains(@class,'x-box-scroller-body-vertical')]` |
| Estimate Proposal BOM | EstimatesProposalBOM | Payments' tab | paymentsTab | BaseWebElement | `//*[@role='dialog' and @aria-hidden='false']//*[@role='tab' and @aria-hidden='false']//span[text()='Payments']` |
| Estimate Proposal BOM | EstimatesProposalBOM | Procurement & Production' tab | proposalBOMTab | BaseWebElement | `//a[@role='tab' and @aria-hidden='false']//span[text()='Procurement & Production']` |
| Estimate Proposal BOM | EstimatesProposalBOM | Proposal BOM' table | proposalBOMTable | proposalBOMTable | `(//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[@class='x-grid-item-container'])` |
| Estimate Proposal BOM | EstimatesProposalBOM | Proposal Requirements' tab | proposalRequirementsTab | BaseWebElement | `//*[@role='dialog' and @aria-hidden='false']//a[@role='tab' and @aria-hidden='false']//span[text()='Proposal Requirements']` |
| Estimate Proposal BOM | EstimatesProposalBOM | PROPOSAL_BOM_TAB | costStructureTab | LongWaitElement | `//a[@aria-selected='true']//span[text()='Proposal BOM']` |
| Estimate Proposal BOM | EstimatesProposalBOM | Regression Test BOM Costing Profile' dropdown | regressionTestBOMCostingProfileDropdown | BaseWebElement | `//*[@role='menu' and @aria-hidden='false']//*[@role='menuitem' and @aria-hidden='false']//*[contains(text(),'Regression Test BOM Costing Profile')]` |
| Estimate Proposal BOM | EstimatesProposalBOM | Remarks' tab | remarksTab | BaseWebElement | `//*[@role='dialog' and @aria-hidden='false']//*[@role='tab' and @aria-hidden='false']//span[text()='Remarks']` |
| Estimate Proposal BOM | EstimatesProposalBOM | Run - All Parts' button | runAllPartsButton | BaseWebElement | `//*[@role='dialog' and not(contains(@class,'x-window-ghost')) and @aria-hidden='false']//*[@role='toolbar' and @aria-hidden='false']//*[@role='button' and @aria-hidden='false']//*[text()='Run - All Parts']` |
| Estimate Proposal BOM | EstimatesProposalBOM | Set Default' button | setDefaultButton | TextElement | `//*[@role='dialog' and not(contains(@class,'x-window-ghost')) and @aria-hidden='false']//*[@role='toolbar' and @aria-hidden='false']//*[@role='button' and @aria-hidden='false'][1]` |
| Estimate Proposal BOM | EstimatesProposalBOM | Tags' tab | tagsTab | BaseWebElement | `//*[@role='dialog' and @aria-hidden='false']//*[@role='tab' and @aria-hidden='false']//span[text()='Tags']` |
| Estimate Proposal BOM | EstimatesProposalBOM | Update & Cost Consolidated BOM' menuItem | updateAndCostConsolidatedBOMMenuItem | BaseWebElement | `//*[@role='menu' and @aria-hidden='false']//*[@role='menuitem' and @aria-hidden='false']//*[contains(text(),'Update & Cost Consolidated BOM')]/ancestor::a` |
| Estimate Structure | EstimatesStructure | Cog' menu | cogMenu | CogMenu | `//table[contains(@class, 'x-grid-item-selected')]//span[@class='x-tree-node-text ']//div[contains(@class, 'ibeCallOut')]` |
| Estimate Structure | EstimatesStructure | Confirm & Release' button | confirmReleaseButton | BaseWebElement | `//span[text()='Confirm & Release']` |
| Estimate Structure | EstimatesStructure | Owner' field | ownerField | EstimatePopUpDropDown | `//span[text()='Owner *']/ancestor::td//input` |
| Estimate Structure | EstimatesStructure | STRUCTURE_TAB | forecastTab | BaseWebElement | `//a[@aria-selected='true']//span[text()='Structure']` |
| Estimate Structure | EstimatesStructure | WBS | wbsTable | WBSTable | `//div[@class='x-grid-item-container' and not(contains(@style, 'transform'))]` |
| Estimate Structure | EstimatesStructure | WBS' tree | wbsTree | SimpleTree | `//div[contains(@class,'x-tree-view x-fit-item x-tree-view-default x-scroller')]` |
| Item list page | itemListPage | ITEM_LISt | itemListContainer | BaseWebElement | `//div[contains(@class,'x-grid-item-container')]` |
| Items page | itemsPage | Columns' dropDown | columnsDropDown | BaseWebElement | `//*[@role='menu' and @aria-hidden='false'][1]` |
| Items page | itemsPage | Columns' option | partNumberColumnOption | BaseWebElement | `//*[@role='menuitem' and @aria-hidden='false']//*[text()='Columns']` |
| Items page | itemsPage | Columns' popUp | columnsPopUp | BaseWebElement | `//*[@role='menu' and @aria-hidden='false'][2]` |
| Items page | itemsPage | Costing' table | costingTable | BillingItemsTable | `//div[@data-testid='costing-table']` |
| Items page | itemsPage | Edit Price Validity' popUp | editPriceValidityPopUp | BaseWebElement | `//*[@role='dialog' and contains(@class,'x-window-default-resizable')]` |
| Items page | itemsPage | Escalate to Commitment' chevron | escalateToCommitmentChevron | BaseWebElement | `//*[text()='Escalate to Commitment' and @data-ref='textInnerEl']/ancestor::*[@role='columnheader' and @aria-hidden='false']//*[@data-ref='triggerEl']` |
| Items page | itemsPage | Escalate to Commitment' header | escalateToCommitmentHeader | BaseWebElement | `//*[text()='Escalate to Commitment' and @data-ref='textInnerEl']/ancestor::*[@role='columnheader' and @aria-hidden='false']` |
| Items page | itemsPage | Items' tab | itemsTab | BaseWebElement | `//a[@aria-selected='true']//span[contains(text(),'Items')]` |
| Items page | itemsPage | Items' table | itemsTable | BillingItemsTable | `//div[@data-testid='items-table']` |
| Items page | itemsPage | Maximum Quantity' column | maximumQuantityColumn | BaseWebElement | `//*[@data-qtip='Maximum quantity the pricing is up to']` |
| Items page | itemsPage | Maximum Quantity' option | maximumQuantityColumnOption | BaseWebElement | `//*[@role='menuitemcheckbox' and @aria-hidden='false']//*[text()='Maximum Quantity']/following-sibling::*[@data-ref='checkEl']` |
| Items page | itemsPage | Part Number' dropDown | partNumberDropDown | BaseWebElement | `//*[@data-columnid='productColumn']//*[contains(@class,'x-form-trigger-focus')]` |
| KEY INFO page | keyInfoPage | Base Unit' field | baseUnitField | IpeDropdownField | `//*[@data-ref='labelTextEl' and contains(text(),'Base Unit')]/ancestor::*[@rowspan='1'][1]//input` |
| KEY INFO page | keyInfoPage | Buyer' field | buyerField | IpeDropdownField | `//*[@data-ref='labelTextEl' and text()='Buyer']/ancestor::*[@rowspan='1'][1]//input` |
| KEY INFO page | keyInfoPage | Data saved successfully' dialog | dataSavedSuccessfullyDialog | BaseWebElement | `//*[@role='dialog' and @aria-hidden='false']` |
| KEY INFO page | keyInfoPage | Date' field | dateField | IpeField | `//*[@data-ref='labelTextEl' and text()='Date']/ancestor::*[@rowspan='1'][1]//input` |
| KEY INFO page | keyInfoPage | Description' field | descriptionField | IpeField | `//*[@data-ref='labelTextEl' and contains(text(),'Description')]/ancestor::*[@rowspan='1'][1]//input` |
| KEY INFO page | keyInfoPage | Estimating Source' field | estimatingSourceField | IpeDropdownField | `//*[@data-ref='labelTextEl' and contains(text(),'Estimating Source')]/ancestor::*[@rowspan='1'][1]//input` |
| KEY INFO page | keyInfoPage | KEY_INFO_TAB | keyInfoTab | BaseWebElement | `//a[@aria-selected='true']//span[contains(text(),'KEY INFO')]` |
| KEY INFO page | keyInfoPage | Organization' field | organizationField | IpeIdDropdownField | `//*[@data-ref='labelTextEl' and text()='Organization']/ancestor::*[@rowspan='1'][1]//input` |
| KEY INFO page | keyInfoPage | Part or Service Number' field | partOfServiceNumberField | IpeField | `//*[@data-ref='labelTextEl' and contains(text(),'Part or Service Number')]/ancestor::*[@rowspan='1'][1]//input` |
| KEY INFO page | keyInfoPage | Part or Service Number' field | partOrServiceNumberField | IpeField | `//*[@data-ref='labelTextEl' and contains(text(),'Part or Service Number')]/ancestor::*[@rowspan='1'][1]//input` |
| KEY INFO page | keyInfoPage | Planning & Estimating' tab | planningAndEstimatingTab | BaseWebElement | `//a[@role='tab']//span[contains(text(),'Planning & Estimating') or contains(text(),'PLANNING & ESTIMATING')]` |
| KEY INFO page | keyInfoPage | Requester' field | requesterField | IpeDropdownField | `//*[@data-ref='labelTextEl' and text()='Requester']/ancestor::*[@rowspan='1'][1]//input` |
| KEY INFO page | keyInfoPage | Select Commodity Code or Material Group' field | selectCommodityCodeOrMaterialGroupField | IpeDropdownField | `//*[@data-ref='labelTextEl' and contains(text(),'Select Commodity Code/Material Group')]/ancestor::*[@rowspan='1'][1]//input` |
| KEY INFO page | keyInfoPage | Supplier Quote Type' field | supplierQuoteTypeField | IpeDropdownField | `//input[@placeholder='Select Document Type' and @aria-hidden='false']` |
| KEY INFO page | keyInfoPage | Supplier Quote Valid From' field | supplierQuoteValidFromField | IpeField | `//*[@data-ref='labelTextEl' and contains(text(),'Valid From')]/ancestor::*[@rowspan='1'][1]//input` |
| KEY INFO page | keyInfoPage | Supplier Quote Valid Until' field | supplierQuoteValidUntilField | IpeField | `//*[@data-ref='labelTextEl' and contains(text(),'Valid Until')]/ancestor::*[@rowspan='1'][1]//input` |
| KEY INFO page | keyInfoPage | Supplier' field | supplierField | IpeIdDropdownField | `//*[@data-ref='labelTextEl' and text()='Supplier']/ancestor::*[@rowspan='1'][1]//input` |
| KEY INFO page | keyInfoPage | Type of Product or Service' field | typeOfProductOrServiceField | IpeDropdownField | `//*[@data-ref='labelTextEl' and contains(text(),'Type of Product or Service')]/ancestor::*[@rowspan='1'][1]//input` |
| KEY INFO page | keyInfoPage | Upload Files' button | uploadFilesButton | BaseWebElement | `//*[text()='Upload Files']/ancestor::a[@aria-hidden='false']` |
| Lines Items page | LinesItemsPage | Click here to add' link | addLink | BaseWebElement | `//div[@aria-hidden='false' and @role='tabpanel']//a[@data-grigaddlink='true']//*[text()]` |
| Lines Items page | LinesItemsPage | Contract Lines Allocations' table | contractLinesAllocTable | ContractLinesCostAllocationsTable | `//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[@class='x-grid-item-container']` |
| Lines Items page | LinesItemsPage | Contract Lines' table | contractLinesTable | ContractLinesTable | `//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-container x-tabpanel-child'))]//div[@class='x-grid-item-container']` |
| Lines Items page | LinesItemsPage | Cost Allocations' tab | costAllocTab | BaseWebElement | `//span[text()='Cost Allocations']` |
| Lines Items page | LinesItemsPage | Delivery Schedule' tab | deliveryScheduleTab | BaseWebElement | `//span[text()='Delivery Schedule']` |
| Lines Items page | LinesItemsPage | Delivery Schedule' table | deliveryScheduleTable | ContractLinesCostAllocationsTable | `//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[@class='x-grid-item-container']` |
| Lines Items page | LinesItemsPage | Line Item Details' window | editPhaseInformationWindow | BaseWebElement | `//*[@role='dialog' and contains(@class,'x-window-default-resizable')]` |
| Lines Items page | LinesItemsPage | LINE_ITEMS_TAB | itemsTab | BaseWebElement | `//a[@aria-selected='true']//span[text()='Line Items']` |
| Lines Items page | LinesItemsPage | Pricing & Qty' tab | pricingTab | BaseWebElement | `//*[@role='dialog']//span[text()='Pricing & Qty' or text()='Pricing']` |
| Lines Items page | LinesItemsPage | Pricing Strategy' field | pricingStrategyField | IpeCustomDropdownField | `(//div//label//span[text()='Pricing Strategy *']//following::input[1])` |
| Lines Items page | LinesItemsPage | Pricing&Qty' table | pricingQtyTable | ContractLinesCostAllocationsTable | `//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[@class='x-grid-item-container']` |
| Lines Items page | LinesItemsPage | Reprice' button | repriceButton | BaseWebElement | `//span[text()='Re-price']` |
| Lines Items page | LinesItemsPage | Revenue Recognition Method' field | revenueRecognitionField | ArrowDropdownField | `(//div//label//span[text()='Revenue Recognition Method ']//following::input[1])` |
| Lines Items page | LinesItemsPage | View' dropdown | viewDropdown | IpeViewDropdownField | `//*[contains(@class, 'x-toolbar') and @aria-hidden='false' and @role='group']//a[contains(@class, 'x-split-button')]` |
| Main page | MainPage | Allen Azar' option | allenAzarOption | BaseWebElement | `//*[@role='option' and text()='Allen Azar']` |
| Main page | MainPage | Bill of Material' tab | billOfMaterialTab | BaseWebElement | `//span[text()='Bill of Material']` |
| Main page | MainPage | Billing Items' tab | billingItemsTab | BaseWebElement | `//a[@aria-hidden='false']//span[contains(text(),'Billing Items')]` |
| Main page | MainPage | Billing Plan' tab | billingPlanTab | BaseWebElement | `//a[@aria-hidden='false']//span[contains(text(),'Billing')]` |
| Main page | MainPage | CLINs' tab | clinsTab | BaseWebElement | `//span[text()='CLINs' or text()='Line Items']` |
| Main page | MainPage | Close Error' dialog | closeErrorDialogButton | BaseWebElement | `//*[@role='dialog' and @aria-hidden='false']//*[@data-ref='toolEl']` |
| Main page | MainPage | Cog Settings' menu | cogMenu | IpeCogTree | `//div[((contains(@class, 'x-fit-item x-panel-default x-grid') or contains(@class,'x-tree-panel x-tree-lines x-grid'))) and contains(@aria-hidden,'false')]//span[contains(@class, 'x-fa fa-cog')]` |
| Main page | MainPage | Column' header options | columnHeaderOptions | BaseWebElement | `//*[@role='menu' and @aria-hidden='false' and @aria-expanded='true']` |
| Main page | MainPage | Columns' option | columnsOption | BaseWebElement | `//*[@role='menuitem' and .//*[text()='Columns']]` |
| Main page | MainPage | Columns' options | columnsOptions | BaseWebElement | `//*[@role='menu' and @aria-hidden='false' and @aria-expanded='true'][2]` |
| Main page | MainPage | Confirm' button | confirmButton | BaseWebElement | `//*[@role='dialog' and @aria-hidden='false' and contains(@class,'x-window x-layer')]//*[@role='toolbar' and @aria-hidden='false' and contains(@class,'x-toolbar-default-docked-bottom')]//*[@role='button' and @aria-hidden='false']//*[text()='Confirm']/ancestor::a` |
| Main page | MainPage | Confirmation Yes' button | confirmationYesDialogButton | BaseWebElement | `//*[@role='alertdialog' and @aria-hidden='false']//*[text()='Yes']` |
| Main page | MainPage | Consolidated BOM' tab | consolidatedBOMTab | IpeHeavyLoadTab | `//*[@role='tab' and @aria-hidden='false']//*[contains(text(),'Consolidated BOM')]` |
| Main page | MainPage | Copy' button | copyButton | BaseWebElement | `//span[text()='Copy']` |
| Main page | MainPage | Cost & Price' status | costPriceStatus | TextElement | `//*[@role='tabpanel' and @aria-hidden='false']//*[@class='x-ibeLinksCls']/parent::div/div[1]` |
| Main page | MainPage | Cost Source History' sub-tab | costSourceHistorySubTab | BaseWebElement | `//*[@role='menu' and @aria-hidden='false']//span[text()='Cost Source History']` |
| Main page | MainPage | Create from Category' dropDown | createFromCategoryDropDown | BaseWebElement | `//*[@role='menu' and @aria-hidden='false'][2]` |
| Main page | MainPage | Create from Category' option | createFromCategoryOption | BaseWebElement | `//*[@role='menu' and @aria-expanded='true' and @aria-hidden='false']//*[@class='x-menu-item-link']//*[contains(text(),'Create from Category')]/ancestor::a[@role='menuitem' and @aria-hidden='false']` |
| Main page | MainPage | Create from Type' dropDown | createFromTypeDropDown | BaseWebElement | `//*[@role='menu' and @aria-hidden='false'][2]` |
| Main page | MainPage | Create from Type' option | createFromTypeOption | BaseWebElement | `//*[@role='menu' and @aria-expanded='true' and @aria-hidden='false']//*[@class='x-menu-item-link']//*[contains(text(),'Create from Type')]/ancestor::a[@role='menuitem' and @aria-hidden='false']` |
| Main page | MainPage | Created On' column | createdOnColumn | BaseWebElement | `//*[@role='columnheader' and @aria-hidden='false']//*[text()='Created On']/ancestor::*[@role='columnheader' and @aria-hidden='false']` |
| Main page | MainPage | Created On' option | createdOnOption | BaseWebElement | `//*[@role='menuitemcheckbox' and .//*[text()='Created On']]` |
| Main page | MainPage | Data saved successfully' popUp | dataSavedSuccessfullyPopUp | LongWaitElement | `//*[text()='Data saved successfully']/ancestor::*[@role='dialog' and @aria-hidden='false']` |
| Main page | MainPage | Default' view | defaultView | BaseWebElement | `//a[@role='menuitem']//*[text()='Default (shared)']` |
| Main page | MainPage | Dialog box View' dropdown | dialogBoxViewDropdown | IpeDialogBoxViewDropDownField | `//*[@role='dialog' and @aria-hidden='false']//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-tabpanel-child'))]//a[contains(@class, 'x-split-button')][@aria-hidden='false']//span[contains(text(), 'View')]` |
| Main page | MainPage | Download' icon | downloadIcon | BaseWebElement | `//*[@role='toolbar' and @aria-hidden='false']//*[@data-qtip='Download']` |
| Main page | MainPage | Download' options | downloadOptions | MenuItemField | `//*[@role='menu' and @aria-hidden='false' and @aria-expanded='true']` |
| Main page | MainPage | Error' dialog | errorDialog | TextElement | `//*[@role='dialog' and @aria-hidden='false']` |
| Main page | MainPage | Estimate for Labor' popup | estimateForLaborPopup | BaseWebElement | `//*[contains(text(),'Estimate for')]/ancestor::*[@role='dialog' and @aria-hidden='false']//*[@role='tab' and @aria-selected='true']` |
| Main page | MainPage | Estimate for Labor' popUp | estimateForLaborPopUp | BaseWebElement | `//*[@role='dialog' and @aria-hidden='false']//*[contains(text(),'Estimate for')]//*[@role='tab' and @aria-selected='true']` |
| Main page | MainPage | Filter by' dropdown | filterByDropdown | IpeCustomDropdownField | `//input[@placeholder='Choose Field']` |
| Main page | MainPage | Filter by' dropDownArrow | filterByDropDownArrow | BaseWebElement | `//input[@placeholder='Choose Field']/parent::*/following-sibling::*` |
| Main page | MainPage | Filter by' options | filterByOptions | MenuItemField | `//*[@role='listbox' and @aria-hidden='false']` |
| Main page | MainPage | Filter' field | filterField | IpeField | `//input[@role='textbox']` |
| Main page | MainPage | Filter' icon | filterIcon | BaseWebElement | `//*[@role='toolbar' and @aria-hidden='false']//*[@data-qtip='Filters']` |
| Main page | MainPage | Filter' selectedTags | filterSelectedTags | TextElement | `//input[@role='textbox']/ancestor::*[@role='combobox' and @aria-hidden='false' and @aria-autocomplete='list']//*[@data-ref='itemList']` |
| Main page | MainPage | First Add Filter' button | firstAddFilterButton | BaseWebElement | `//*[@role='grid' and @aria-hidden='false']//*[@role='toolbar' and @aria-hidden='false'][1]//*[contains(@class,'fa-plus')]` |
| Main page | MainPage | First Filter' combobox | firstFilterCombobox | IpeField | `//*[@role='combobox' and @aria-hidden='false']` |
| Main page | MainPage | First Filter' dropbox | firstFilterDropbox | BaseWebElement | `//*[@role='listbox' and @aria-hidden='false']` |
| Main page | MainPage | First Filter' dropdown | firstFilterDropdown | BaseWebElement | `//*[@role='grid' and @aria-hidden='false']//*[@role='toolbar' and @aria-hidden='false'][1]//*[contains(@class,'x-form-arrow-trigger-default')]` |
| Main page | MainPage | First Filter' input | firstFilterInput | IpeField | `//input[@role='textbox' and @aria-hidden='false']` |
| Main page | MainPage | first' proposal | firstProposal | BaseWebElement | `//table[1]//tr/td[@data-columnid='quoteSearchText']/div` |
| Main page | MainPage | Hamburger' menu | hamburgerMenu | IpeHumburgerTree | `//a[@data-qtip='More']` |
| Main page | MainPage | Hardware & Software' tab | hwSwTab | BaseWebElement | `//span[text()='Hardware & Software']` |
| Main page | MainPage | Items' tab | itemsTab | BaseWebElement | `//a//span[contains(text(),'Items')]` |
| Main page | MainPage | Labor' tab | laborTab | BaseWebElement | `//span[text()='Labor' or text()='Engineering' or text()='Labor Resources']` |
| Main page | MainPage | Lines Items' tab | lineItemsTab | BaseWebElement | `//span[text()='Line Items']` |
| Main page | MainPage | List View' dropdown | listViewDropdown | IpeViewDropdownField | `//*[@role='toolbar' and @aria-hidden='false']//a[contains(@class, 'x-split-button')][@aria-hidden='false']//span[contains(text(), 'View')]` |
| Main page | MainPage | Margin as Percentage' widget | marginAsPercentageWidget | TextElement | `//*[@role='tabpanel' and @aria-hidden='false']//*[@data-qtip='Margin as Percentage']` |
| Main page | MainPage | Master Data' options | masterDataOptions | BaseWebElement | `//*[@role='menu' and @aria-hidden='false']` |
| Main page | MainPage | Master Data' tab | masterDataTab | BaseWebElement | `//*[@role='button' and @aria-hidden='false']//*[contains(text(),'Master Data') or contains(text(),'MASTER DATA')]` |
| Main page | MainPage | Material Cost Estimates' options | materialCostEstimatesOptions | MenuItemField | `//*[@role='menu' and @aria-hidden='false']` |
| Main page | MainPage | Material Cost Estimates' tab | materialCostEstimatesTab | BaseWebElement | `//*[@role='button' and @aria-hidden='false']//*[contains(text(),'MATERIAL COST ESTIMATES')]/ancestor::*[@role='button' and @aria-hidden='false']` |
| Main page | MainPage | Material Cost' check | materialCostCheck | BaseWebElement | `//span[@aria-label='Material Cost']` |
| Main page | MainPage | MenuItems | menuItems | MenuItemField | `//*[@role='menu' and @aria-hidden='false' and @aria-expanded='true'][last()]` |
| Main page | MainPage | Mfg Part' popUp | mfgPartPopUp | BaseWebElement | `//*[@role='dialog' and @aria-hidden='false']//*[contains(text(),'Mfg')]` |
| Main page | MainPage | New' button | newButton | BaseWebElement | `//span[text()='New']/ancestor::a` |
| Main page | MainPage | New' options | newOptions | MenuItemField | `//*[@role='menu' and @aria-hidden='false']` |
| Main page | MainPage | Next' button | nextButton | BaseWebElement | `//span[text()='Next']` |
| Main page | MainPage | No records found, click here to add' button | noRecordFind | LongWaitElement | `//*[@role='tabpanel' and @aria-hidden='false']//*[@aria-hidden='false' and (@role='grid' or @role='treegrid')]//*[@data-grigaddlink='true']//div[text()]` |
| Main page | MainPage | Open Proposal' menuItem | openProposalMenuItem | MenuItemField | `//*[@role='menu' and @aria-hidden='false']//*[contains(@class,'x-menu-item x-menu-item-default x-box-item')]//*[text()='Open Proposal']` |
| Main page | MainPage | Open' menuItem | openMenuItem | BaseWebElement | `//*[@role='menu' and @aria-hidden='false']//*[contains(@class,'x-menu-item x-menu-item-default x-box-item')]//*[text()='Open']` |
| Main page | MainPage | Open' menuItems | openMenuItems | MenuItemField | `//*[@role='menu' and @aria-hidden='false' and @aria-expanded='true'][last()]//*[@role='menuitem' and @aria-hidden='false'] ` |
| Main page | MainPage | Other Cost' widget | otherCostWidget | TextElement | `//*[@role='tabpanel' and @aria-hidden='false']//*[@data-qtip='Other cost']` |
| Main page | MainPage | Other Revenue' widget | otherRevenueWidget | TextElement | `//*[@role='tabpanel' and @aria-hidden='false']//*[@data-qtip='Other revenue']` |
| Main page | MainPage | Owner' column | ownerColumn | ListColumn | `//*[@data-columnid='quoteSearchResponsibleID']` |
| Main page | MainPage | Owner' column chevron | ownerColumnChevron | BaseWebElement | `//*[@role='columnheader' and @aria-hidden='false']//*[text()='Owner']/ancestor::*[@role='columnheader' and @aria-hidden='false']//*[@data-ref='triggerEl']` |
| Main page | MainPage | Owner' column header | ownerColumnHeader | BaseWebElement | `//*[@role='columnheader' and @aria-hidden='false']//*[text()='Owner']/ancestor::*[@role='columnheader' and @aria-hidden='false']` |
| Main page | MainPage | Owner' dropDownArrow | ownerDropDownArrow | BaseWebElement | `//*[@data-ref='placeholderLabel' and text()='All Owner']/ancestor::*[@data-ref='triggerWrap']//*[contains(@class,'x-form-arrow-trigger')]` |
| Main page | MainPage | Owner' EstimatePopup | ownerEstimatePopUp | IpeDropdownField | `//*[@role='dialog' and @aria-hidden='false']//*[text()='Owner *']/ancestor::label/following-sibling::*//input` |
| Main page | MainPage | Phases' tab | phasesTab | BaseWebElement | `//span[contains(text(),'Phases')]` |
| Main page | MainPage | plus' searchIcon | plusSearchIcon | BaseWebElement | `//*[@role='grid' and @aria-hidden='false']//*[@role='toolbar' and @aria-hidden='false']//*[contains(@class,'fa-plus')]` |
| Main page | MainPage | Pricing' tab | pricingTab | BaseWebElement | `//a//span[text()='Pricing']` |
| Main page | MainPage | Procurement & Production' tab | procurementProductionTab | BaseWebElement | `//span[text()='Procurement & Production']` |
| Main page | MainPage | Product or Service Price Catalog' dropdown | productOrServicePriceCatalogDropdown | IpeDropdownField | `//*[@role='tabpanel' and @aria-hidden='false']//*[contains(text(),'Product/Service') or contains(text(),'Price Catalog')]/ancestor::label/following-sibling::*//input` |
| Main page | MainPage | Products & Services' sub-tab | productsAndServicesSubTab | BaseWebElement | `//*[@role='menu' and @aria-hidden='false']//span[text()='Products & Services']` |
| Main page | MainPage | Proposal View' dropdown | proposalViewDropdown | IpeViewDropdownField | `//*[@role='toolbar' and @aria-hidden='false']//a[contains(@class, 'x-split-button')][@aria-hidden='false']//span[contains(text(), 'View:') or contains(text(), 'Select or Create View')]` |
| Main page | MainPage | Proposal' option | firstFilterOption | BaseWebElement | `//li[@role='option' and text()='Proposal']` |
| Main page | MainPage | Proposals' tab | proposalsTab | BaseWebElement | `//a//span[contains(text(),'Proposals') or contains(text(),'PROPOSALS')]` |
| Main page | MainPage | Purchased Part' option | purchasedPartOption | BaseWebElement | `//*[@role='menuitem' and @aria-hidden='false']//*[contains(text(),'Purchased Part')]/ancestor::a` |
| Main page | MainPage | Refresh' icon | refreshIcon | BaseWebElement | `//*[contains(@class,'PricingAppTitleToolbar')]//*[@role='toolbar' and @aria-hidden='false']//*[@data-qtip='Reload' and @aria-hidden='false']//*[@data-ref='btnEl']` |
| Main page | MainPage | Regression Test Proposal for Copy Scenario - Manufacturing' proposal | regressionTestProposalForCopyScenarioManufacturingProposal | BaseWebElement | `//*[@data-columnid='quoteSearchText']//*[contains(text(),'Regression Test Proposal for Copy Scenario - Manufacturing')]` |
| Main page | MainPage | Reload' button | reloadButton | BaseWebElement | `//a[@data-qtip='Reload']` |
| Main page | MainPage | Reload' page | reloadSymbol | BaseWebElement | `//*[@data-qtip='Reload']` |
| Main page | MainPage | Reschedule Proposal' menuItem | rescheduleProposalMenuItem | BaseWebElement | `//*[text()='Reschedule Proposal']/ancestor::*[@role='menuitem' and @aria-hidden='false']` |
| Main page | MainPage | Save' button | saveButton | SaveButton | `//a[@data-qtip='Save']` |
| Main page | MainPage | Search' icon | searchIcon | BaseWebElement | `//*[contains(@class,'PricingAppTitleToolbar')]//*[@role='toolbar' and @aria-hidden='false']//*[@data-qtip='Search' and @aria-hidden='false']//*[@data-ref='btnEl']` |
| Main page | MainPage | Sets' tab | setsTab | BaseWebElement | `//a[@aria-hidden='false']//span[contains(text(),'Sets')]` |
| Main page | MainPage | SETUP' options | setupOptions | MenuItemField | `//*[@role='menu' and @aria-hidden='false']` |
| Main page | MainPage | Setup' tab | setupTab | BaseWebElement | `//a//span[contains(text(),'Setup')]` |
| Main page | MainPage | SETUP' tab | SETUPTab | BaseWebElement | `//a//span[contains(text(),'SETUP')]` |
| Main page | MainPage | Status & logs' link | statusLogsLink | TextElement | `//*[@role='tabpanel' and @aria-hidden='false']//*[@subtitlelink='showJobStatus']` |
| Main page | MainPage | Status' widget | statusWidget | BaseWebElement | `//*[@role='tabpanel' and @aria-hidden='false']//*[@class='x-ibeLinksCls']/ancestor::*[contains(@class,'x-container-default x-scroller')]` |
| Main page | MainPage | Supplier Quote' option | supplierQuoteOption | BaseWebElement | `//*[@role='menu' and @aria-hidden='false']//*[@role='menuitem' and @aria-hidden='false']//*[contains(text(),'Supplier Quote')]/ancestor::a` |
| Main page | MainPage | Supplier RFP’s' notification | supplierRFPsNotification | IpeNotification | `//*[contains(text(),'RFP') and @data-ref='innerCt']/ancestor::*[@role='dialog' and @aria-hidden='false']` |
| Main page | MainPage | TITLE_TOOLBAR | titleToolbar | LongWaitElement | `//div[contains(@class, 'x-PricingAppNavTitleToolbar')]` |
| Main page | MainPage | top menu | topMenu | MenuItemField | `//*[(@role='toolbar' and @aria-hidden='false') or contains(@class,'PricingAppSearchContainer')]//*[contains(@class,'TopMenuButton') and @aria-hidden='false']` |
| Main page | MainPage | top menu 'Master Data | masterDataTopMenu | MenuItemField | `//*[@role='menu' and @aria-hidden='false' and @aria-expanded='true']//*[@role='menuitem' and @aria-hidden='false']` |
| Main page | MainPage | top menu 'Material Cost Estimates | materialCostEstimatesTopMenu | MenuItemField | `//*[@role='menu' and @aria-hidden='false' and @aria-expanded='true']//*[@role='menuitem' and @aria-hidden='false']` |
| Main page | MainPage | top menu 'Setup | setupTopMenu | MenuItemField | `//*[@role='menu' and @aria-hidden='false' and @aria-expanded='true']//*[@role='menuitem' and @aria-hidden='false']` |
| Main page | MainPage | top menu 'Users | usersTopMenu | MenuItemField | `//*[@role='menu' and @aria-hidden='false' and @aria-expanded='true']//*[@role='menuitem' and @aria-hidden='false']` |
| Main page | MainPage | Total Cost' widget | totalCostWidget | TextElement | `//*[@role='tabpanel' and @aria-hidden='false']//*[@data-qtip='Total cost in the WBS']` |
| Main page | MainPage | Total Price' widget | totalPriceWidget | TextElement | `//*[@role='tabpanel' and @aria-hidden='false']//*[@data-qtip='Total Price' or @data-qtip='Total Revenue']` |
| Main page | MainPage | Travel Expenses' widget | travelCostWidget | TextElement | `//*[@role='tabpanel' and @aria-hidden='false']//*[@data-qtip='Travel & Other expenses']` |
| Main page | MainPage | Travel Revenue' widget | travelRevenueWidget | TextElement | `//*[@role='tabpanel' and @aria-hidden='false']//*[@data-qtip='Travel & Other revenue']` |
| Main page | MainPage | UNLOCK FOR OTHERS' link | unlockForOthersLink | BaseWebElement | `//*[@data-qtip='Edit mode - Click to display (unlocks for other users)' and @aria-disabled='false']` |
| Main page | MainPage | Update Cost & Prices' link | updateCostPriceLink | BaseWebElement | `//*[@role='tabpanel' and @aria-hidden='false']//*[@titlelink='updateCostsWithFormula' or @titlelink='updateBoeCostsWithFormula']` |
| Main page | MainPage | Update Cost & Prices' link | updateCostAndPricesLink | BaseWebElement | `//div[normalize-space()='Update Cost & Prices']` |
| Main page | MainPage | Update Cost' button | updCostButton | BaseWebElement | `//*[@titlelink='updateCostsWithFormula']` |
| Main page | MainPage | Update Estimate totals' link | UpdateEstimateTotalsLink | TextElement | `//*[@role='tabpanel' and @aria-hidden='false']//*[@titlelink='updateBoeCostsWithFormula']` |
| Main page | MainPage | Users' options | usersOptions | MenuItemField | `//*[@role='menu' and @aria-hidden='false']` |
| Main page | MainPage | Users' tab | usersTab | BaseWebElement | `//*[@role='button' and @aria-hidden='false']//span[contains(text(),'tech twenty5')]` |
| Main page | MainPage | View' dropArrow | viewDropArrow | BaseWebElement | `//*[contains(@class, 'x-toolbar') and @aria-hidden='false' and @role='group']//a[@aria-hidden='false' and contains(@class, 'x-split-button')]//span[@data-ref='arrowEl']` |
| Main page | MainPage | View' dropArrow2 | viewDropArrow2 | BaseWebElement | `//*[contains(@class, 'x-toolbar')]//a[contains(@class, 'x-split-button')]` |
| Main page | MainPage | View' dropdown | viewDropdown | IpeViewDropdownField | `//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-tabpanel-child'))]//a[contains(@class, 'x-split-button')][@aria-hidden='false']//span[contains(text(), 'View')]` |
| Main page | MainPage | WBS Cost Structure' tab | wbsCostStructureTab | BaseWebElement | `//a[@role='tab' and @aria-hidden='false']//span[contains(text(),'WBS Cost Structure')]` |
| Main page | MainPage | WBS Structure' tab | wbsStructureTab | BaseWebElement | `//a//span[text()='WBS Structure' or contains(text(),'Cost Structure') or contains(text(),'Workstreams')]` |
| Main page | MainPage | WBS View' dropdown | wbsViewDropdown | IpeViewDropdownField | `//*[contains(@class, 'x-toolbar') and @aria-hidden='false' and @role='group']//a[contains(@class, 'x-split-button')]` |
| Main page | MainPage | WBS' tab | wbsTab | BaseWebElement | `//span[text()='WBS' or text()='Workstreams']` |
| Main page | MainPage | Yes' dialogButton | yesDialogButton | BaseWebElement | `//*[@role='alertdialog' and @aria-hidden='false']//*[text()='Yes']` |
| Opportunity Details page | OpportunityDetails | BOE copy is happening and Reschedule' warning | boeCopyProjectRescheduleWarningPopUp | LongWaitElement | `//*[text()='Data saved successfully']/ancestor::*[@role='dialog' and @aria-hidden='false']` |
| Opportunity Details page | OpportunityDetails | Capability' dropdown | leadingCapabilityDropdown | BaseWebElement | `//input[@placeholder='Select Company']/parent::div/following-sibling::div` |
| Opportunity Details page | OpportunityDetails | Capability' field | capabilityField | IpeDropdownField | `//input[@placeholder='Select Company']` |
| Opportunity Details page | OpportunityDetails | Client Customer sell-to' dropdown | clientFieldDropdown | BaseWebElement | `//input[@placeholder='Enter company  name to search or click arrow for list']/parent::div/following-sibling::div` |
| Opportunity Details page | OpportunityDetails | Client Customer sell-to' field | clientField | IpeDropdownField | `//input[@placeholder='Enter company  name to search or click arrow for list']` |
| Opportunity Details page | OpportunityDetails | Close' button | closeButton | BaseWebElement | `//span[text()='Close']/ancestor::a` |
| Opportunity Details page | OpportunityDetails | Company' field | companyField | IpeDropdownField | `//input[@placeholder='Select Company']` |
| Opportunity Details page | OpportunityDetails | Confirm' button | confirmButton | BaseWebElement | `//span[text()='Confirm']/ancestor::a` |
| Opportunity Details page | OpportunityDetails | Cost Center' field | costCenterField | IpeField | `//tr//span[text()='Cost Center']//following::div[3]//input` |
| Opportunity Details page | OpportunityDetails | COST_STRUCTURE_TAB | costStructureTab | BaseWebElement | `//a[@aria-selected='false']//span[contains(text(),'Cost Structure')]` |
| Opportunity Details page | OpportunityDetails | CRM Opportunity' dropdown | crmFieldDropdown | BaseWebElement | `//tr//span[text()='CRM Opportunity']//following::input[1]/parent::div/following-sibling::div` |
| Opportunity Details page | OpportunityDetails | CRM Opportunity' field | crmField | IpeDropdownField | `//tr//span[text()='CRM Opportunity']//following::input[1]` |
| Opportunity Details page | OpportunityDetails | Customer Currency' dropdown | customerCurrencyDropdown | BaseWebElement | `//*[@placeholder='Default=USD']/parent::div/following-sibling::div` |
| Opportunity Details page | OpportunityDetails | Customer Currency' field | customerCurrencyField | IpeDropdownField | `//*[@placeholder='Default=USD']` |
| Opportunity Details page | OpportunityDetails | Data saved successfully' popUp | dataSavedSuccessfullyPopUp | LongWaitElement | `//*[text()='Data saved successfully']/ancestor::*[@role='dialog' and @aria-hidden='false']` |
| Opportunity Details page | OpportunityDetails | Description' field | projectDescriptionField | IpeField | `//input[@placeholder='Enter a brief description of the bid (something you can remember to find it later by)']` |
| Opportunity Details page | OpportunityDetails | Edit Rates' link | editRatesLink | BaseWebElement | `//a[text()='Edit Rates']` |
| Opportunity Details page | OpportunityDetails | End' field | selectEndField | IpeEndDateField | `//input[@placeholder='Select End']` |
| Opportunity Details page | OpportunityDetails | Error' dialog | errorDialog | TextElement | `//*[@role='dialog' and @aria-hidden='false' and contains(@class,'x-closable')]` |
| Opportunity Details page | OpportunityDetails | Escalation and Inflammation Rates' table | escalationAndInflammationRatesTable | EscalationInflationRatesTable | `(//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-window-item x-panel-default'))]//div[@class='x-grid-item-container'])[2]` |
| Opportunity Details page | OpportunityDetails | Escalation first' cell | escalationFirstCell | BaseWebElement | `//*[@role='dialog']//*[@class='x-grid-checkcolumn']/ancestor::td[@role='gridcell']` |
| Opportunity Details page | OpportunityDetails | Escalation or Inflation Rates' tab | escalationOrInflationRates | BaseWebElement | `//span[contains(text(),'Escalation or Inflation Rates')]` |
| Opportunity Details page | OpportunityDetails | Force Update Yes' button | forceUpdateYesButton | BaseWebElement | `//*[@role='alertdialog' and @aria-disabled='false']//*[text()='Yes']` |
| Opportunity Details page | OpportunityDetails | Force Update' popUp | forceUpdatePopUp | LongWaitElement | `//*[@role='alertdialog' and @aria-disabled='false']` |
| Opportunity Details page | OpportunityDetails | Full Screen' option | fullScreenOption | BaseWebElement | `//*[@class='tox-tiered-menu']//*[@role='menu']//*[@aria-label='Fullscreen' and @role='menuitem']` |
| Opportunity Details page | OpportunityDetails | Keep Exact Dates' radioButton | keepExactDatesRadioButton | BaseWebElement | `//label[text()='Keep exact dates']/parent::*//input[@aria-hidden='false']` |
| Opportunity Details page | OpportunityDetails | Leading Company Currency' field | companyCurrencyField | IpeField | `//span[text()='Leading Company Currency *']//following::div[3]//input` |
| Opportunity Details page | OpportunityDetails | Leading Company' dropdown | leadingCompanyDropdown | BaseWebElement | `//input[@placeholder='Select Company']/parent::div/following-sibling::div` |
| Opportunity Details page | OpportunityDetails | Leading Company' field | leadingCompanyField | IpeDropdownField | `//input[@placeholder='Select Company']` |
| Opportunity Details page | OpportunityDetails | Leading Department' field | leadingDepartmentField | IpeDropdownField | `//input[@placeholder='Select Department']` |
| Opportunity Details page | OpportunityDetails | Leading Site' dropdown | leadingSiteDropdown | BaseWebElement | `//input[@placeholder='Select Department']/parent::div/following-sibling::div` |
| Opportunity Details page | OpportunityDetails | Leading Site' field | leadingSiteField | IpeDropdownField | `//input[@placeholder='Select Department']` |
| Opportunity Details page | OpportunityDetails | Leading Site' menuItem | leadingSiteMenuitem | MenuItemField | `//*[@data-ref='listWrap']/ul[@class='x-list-plain' and @aria-hidden='false']` |
| Opportunity Details page | OpportunityDetails | Maintain Proposal-specific Currency Exchange Rates' popup | exchangePopup | BaseWebElement | `//*[@role='dialog' and @aria-hidden='false']//*[contains(text(),'Exchange') and @data-ref='textEl']` |
| Opportunity Details page | OpportunityDetails | Maintain Proposal-specific Currency Exchange Rates' popup | currencyExchangeRatesPopup | BaseWebElement | `//*[@aria-hidden='false' and @role='dialog']//*[text()='Maintain Proposal-specific Currency Exchange Rates']` |
| Opportunity Details page | OpportunityDetails | Normal' option | normalOption | BaseWebElement | `//*[@class='tox-tiered-menu']//*[@role='menu']//*[@aria-label='Normal' and @role='menuitem']` |
| Opportunity Details page | OpportunityDetails | Open Project in SAP' link | openProjectInSapLink | BaseWebElement | `//a[@class='x-ibeLinksCls ' and text()='Open Project in SAP']` |
| Opportunity Details page | OpportunityDetails | Planned Start' field | selectStartField | IpeField | `//input[@placeholder='Select Start']` |
| Opportunity Details page | OpportunityDetails | Pricing' tab | pricingTab | BaseWebElement | `//a[@aria-hidden='false' and @role='tab']//span[text()='Pricing']` |
| Opportunity Details page | OpportunityDetails | Profit Center' dropdown | profitCenterDropdown | BaseWebElement | `//tr//span[text()='Profit Center']//following::div[4]/parent::div/following-sibling::div` |
| Opportunity Details page | OpportunityDetails | Profit Center' field | profitCenterField | IpeDropButton | `//tr//span[text()='Profit Center']//following::div[4]` |
| Opportunity Details page | OpportunityDetails | Project Currency' field | projectCurrencyField | IpeDropdownField | `//input[@placeholder='Default=USD']` |
| Opportunity Details page | OpportunityDetails | Project Goals or Remarks' field | projectGoalsOrRemarksField | IPEIFrameElement | `//iframe[@title='Rich Text Area']` |
| Opportunity Details page | OpportunityDetails | Project Goals or Remarks' textbox | projGoalRemTextBox | BaseWebElement | `//iframe[@class='tox-edit-area__iframe']` |
| Opportunity Details page | OpportunityDetails | proposal elements to create or copy' radioButtons | proposalElementsToCreateOrCopy | BaseWebElement | `//label[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'select a template')]` |
| Opportunity Details page | OpportunityDetails | Proposal Type' field | projectTypeField | IpeDropdownField | `//input[contains(@id,'iBEBusObjType')]` |
| Opportunity Details page | OpportunityDetails | Reschedule Project' window | rescheduleProjectWindow | BaseWebElement | `//div[contains(@class, 'x-window')]//*[text()='Reschedule Project']` |
| Opportunity Details page | OpportunityDetails | Reschedule' button | rescheduleButton | BaseWebElement | `//a[@role='button' and @aria-hidden='false']//span[text()='Reschedule']` |
| Opportunity Details page | OpportunityDetails | Reset all Rates to 1' option | ratesTo1Option | BaseWebElement | `//*[text()='Turn-off Escalation (Reset all Rates to 1)']/ancestor::a` |
| Opportunity Details page | OpportunityDetails | Reset dates to' radio | resetDatesToRadio | IpeRadioButton | `//label[contains(text(),'Reset dates to')]` |
| Opportunity Details page | OpportunityDetails | Rich Text Editor View Dropdown' button | richTextEditorViewDropdown | BaseWebElement | `//button[@data-mce-name='view']//div` |
| Opportunity Details page | OpportunityDetails | Save Confirmation' popUp | saveConfirmationPupUp | LongWaitElement | `//*[@role='dialog' and @aria-disabled='false']//*[text()='Save Confirmation']` |
| Opportunity Details page | OpportunityDetails | Search Box' field | searchBoxField | IpeTemplateDropdownField | `//input[@role='combobox' and @aria-hidden='false' and contains(@data-componentid, 'iBESearchComboBox')]` |
| Opportunity Details page | OpportunityDetails | Search for historical projects to copy' field | historicalProjectsFromLibraryField | IpeTemplateDropdownField | `//tr//span[text()='Search for historical projects to copy']//following::input[1]` |
| Opportunity Details page | OpportunityDetails | Search for Template from Library' field | templateFromLibraryField | IpeTemplateDropdownField | `//tr//legend//*[contains(text(),'Template')]/ancestor::td//input[@role='combobox']` |
| Opportunity Details page | OpportunityDetails | Select a prior bid' radio | priorProposalRadio | IpeRadioButton | `//label[text()='Select a prior bid']` |
| Opportunity Details page | OpportunityDetails | Select a prior project' radio | priorProposalProject | IpeRadioButton | `//label[text()='Select a prior project' or text()='Select a prior proposal' or text()='Select a Prior Project']` |
| Opportunity Details page | OpportunityDetails | SETUP_TAB | setupTab | LongWaitElement | `//a[@aria-selected='true']//span[contains(text(),'Setup') or contains(text(),'Opportunity Details')]` |
| Opportunity Details page | OpportunityDetails | Several' radio | severalRadio | IpeRadioButton | `//label[text()='Several']` |
| Opportunity Details page | OpportunityDetails | Single phase' radio | singlePhaseRadio | IpeRadioButton | `//label[text()='Single phase']` |
| Opportunity Details page | OpportunityDetails | Text edit' toolbox | textEditToolbox | BaseWebElement | `//*[@data-alloy-vertical-dir='toptobottom' and @style='display: block;']` |
| Opportunity Details page | OpportunityDetails | Update Rates' button | updateRatesButton | BaseWebElement | `//a[@aria-hidden='false']//*[text()='Update Rates']/ancestor::a` |
| Opportunity Details page | OpportunityDetails | Upload RFx Files' button | uploadRfxFiles | BaseWebElement | `//a[contains(@id,'iBEFileUploadButton')]` |
| Opportunity Details page | OpportunityDetails | Yes and Update Costs' button | yesAndUpdateCostsButton | BaseWebElement | `//*[@role='dialog' and @aria-disabled='false']//*[text()='Yes and Update Costs']` |
| Opportunity Details page | OpportunityDetails | Yes' button | yesButton | BaseWebElement | `//*[@role='dialog' and @aria-disabled='false']//*[text()='Yes']` |
| Opportunity Details page | OpportunityDetails | Your Company' field | yourCompanyField | IpeDropdownField | `//*[@placeholder='Select Company']` |
| Phases page | PhasesPage | Cancel' button | cancelButton | BaseWebElement | `//div[@role='toolbar']//a[@role='button']//span[text()='Cancel']` |
| Phases page | PhasesPage | Click here to add' link | addLink | BaseWebElement | `//div[contains(@class, 'x-panel-body')]//a[text()='click here to add']` |
| Phases page | PhasesPage | Close' button | closeButton | BaseWebElement | `//span[text()='Close']` |
| Phases page | PhasesPage | COST_STRUCTURE_TAB | costStructureTab | BaseWebElement | `//a[@aria-selected='false']//span[contains(text(),'Cost Structure')]` |
| Phases page | PhasesPage | Edit Phase Information' window | editPhaseInformationWindow | BaseWebElement | `//div[contains(@class, 'x-window')]//*[text()='Edit Phase Information']` |
| Phases page | PhasesPage | End' field | selectEndField | IpeField | `(//div[contains(@class, 'x-window-body')]//input[@title='Expected date format n/j/y.'])[2]` |
| Phases page | PhasesPage | No records found, click here to add' button | noRecordFind | BaseWebElement | `//*[@data-grigaddlink='true']//div[text()]` |
| Phases page | PhasesPage | PHASES_TAB | phasesTab | BaseWebElement | `//a[@aria-selected='true']//span[contains(text(),'Phases')]` |
| Phases page | PhasesPage | Phases' table | phasesTable | PhasesTable | `//div[@class='x-grid-item-container']` |
| Phases page | PhasesPage | Reschedule Project' window | rescheduleProjectWindow | BaseWebElement | `//div[contains(@class, 'x-window')]//*[text()='Reschedule Project']` |
| Phases page | PhasesPage | Setup' tab | setupTab | BaseWebElement | `//span[text()='Setup']` |
| Phases page | PhasesPage | Start' field | selectStartField | IpeField | `(//div[contains(@class, 'x-window-body')]//input[@title='Expected date format n/j/y.'])[1]` |
| Phases page | PhasesPage | View' dropdown | viewDropdown | IpeViewDropdownField | `//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-tabpanel-child'))]//a[contains(@class, 'x-split-button')][@aria-hidden='false']//span[contains(text(), 'View')]` |
| Phases page | PhasesPage | WBS' tab | wbsTab | BaseWebElement | `//span[text()='WBS' or text()='Workstreams']` |
| Planning & Estimating page | planningEstimatingPage | Data saved successfully' dialog | dataSavedSuccessfullyDialog | BaseWebElement | `//*[@role='dialog' and @aria-hidden='false']` |
| Planning & Estimating page | planningEstimatingPage | Escalation Factor' field | escalationFactorField | IpeDropdownField | `//*[@data-ref='labelTextEl' and contains(text(),'Escalation Factor')]/ancestor::*[@rowspan='1'][1]//input` |
| Planning & Estimating page | planningEstimatingPage | How are proposal BOM quantities consolidated for costing' field | howAreProposalBOMQuantitiesConsolidatedForCostingField | IpeCustomDropdownField | `//*[@data-ref='labelTextEl' and contains(text(),'How are proposal BOM quantities consolidated for costing')]/ancestor::*[@rowspan='1'][1]//input` |
| Planning & Estimating page | planningEstimatingPage | How should the system estimate cost of this item, product and service' field | howShouldTheSystemEstimateCostOfThisItemProductAndServiceField | IpeDropdownField | `//*[@data-ref='labelTextEl' and contains(text(),'How should the system estimate cost of this item')]/ancestor::*[@rowspan='1'][1]//input` |
| Planning & Estimating page | planningEstimatingPage | PLANNING_AND_ESTIMATING_TAB | planningAndEstimatingTab | BaseWebElement | `//a[@role='tab' and @aria-selected='true']//span[contains(text(),'Planning & Estimating') or contains(text(),'PLANNING & ESTIMATING')]` |
| Planning & Estimating page | planningEstimatingPage | Procurement Lead time in days' field | procurementLeadTimeInDaysField | IpeField | `//*[@data-ref='labelTextEl' and (contains(text(),'Procurement Lead-time (in days)') or contains(text(),'Procurement Lead-time'))]/ancestor::*[@rowspan='1'][1]//input` |
| Planning & Estimating page | planningEstimatingPage | Quantity Curve' field | quantityCurveField | IpeDropdownField | `//*[@data-ref='labelTextEl' and contains(text(),'Quantity Curve')]/ancestor::*[@rowspan='1'][1]//input` |
| Planning & Estimating page | planningEstimatingPage | Receipt & Inspection Time' field | receiptAndInspectionTimeField | IpeField | `//*[@data-ref='labelTextEl' and contains(text(),'Receipt & Inspection Time')]/ancestor::*[@rowspan='1'][1]//input` |
| Planning & Estimating page | planningEstimatingPage | Which quantity rule should be used to help estimate price' field | whichQuantityRuleShouldBeUsedToHelpEstimatePriceField | IpeCustomDropdownField | `//*[@data-ref='labelTextEl' and contains(text(),'Which quantity rule should be used to help estimate price')]/ancestor::*[@rowspan='1'][1]//input` |
| Pricing page | PricingPage | Cancel' button | cancelButton | BaseWebElement | `//*[@role='dialog' and @aria-hidden='false']//*[text()='Cancel']/ancestor::a[@aria-hidden='false']` |
| Pricing page | PricingPage | Clear Dates' button | clearDatesButton | BaseWebElement | `//*[@role='dialog' and @aria-hidden='false']//*[text()='Clear Dates']/ancestor::a[@aria-hidden='false']` |
| Pricing page | PricingPage | Confirm' button | confirmButton | BaseWebElement | `//*[@role='dialog' and @aria-hidden='false']//*[text()='Confirm']/ancestor::a[@aria-hidden='false']` |
| Pricing page | PricingPage | Cost Structure' tab | costStructureTab | BaseWebElement | `//*[@role='tab' and @aria-hidden='false']//span[text()='Cost Structure']` |
| Pricing page | PricingPage | Costing' table | costingTable | BillingItemsTable | `//div[@data-testid='costing-table']` |
| Pricing page | PricingPage | Data saved successfully' popUp | dataSavedSuccessfullyPopUp | LongWaitElement | `//*[text()='Data saved successfully']/ancestor::*[@role='dialog' and @aria-hidden='false']` |
| Pricing page | PricingPage | Edit Price Validity' popUp | editPriceValidityPopUp | BaseWebElement | `//*[@role='dialog' and contains(@class,'x-window-default-resizable')]` |
| Pricing page | PricingPage | How are Labor/Item Rates Calculated' field | howAreLaborItemRatesCalculatedField | IpeDropdownField | `//*[@placeholder='T&M Billing PriceBook']` |
| Pricing page | PricingPage | PRICING_TAB | phasesTab | BaseWebElement | `//a[@aria-selected='true']//span[contains(text(),'Pricing')]` |
| Pricing page | PricingPage | SETUP_TAB | setupTab | BaseWebElement | `//a[@aria-selected='false']//span[contains(text(),'Setup')]` |
| Pricing page | PricingPage | Valid From' field | validFromField | IpeField | `//*[@role='dialog' and @aria-hidden='false']//tr//td[1]//*[contains(@name,'iBEDateTime')]` |
| Pricing page | PricingPage | Valid To' field | validToField | IpeField | `//*[@role='dialog' and @aria-hidden='false']//tr//td[2]//*[contains(@name,'iBEDateTime')]` |
| Procurement & Production page | ProcurementAndProductionPage | Amortization Schedule' table | laborAmortizationTable | LaborAmortizationTable | `(//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[@class='x-grid-item-container'])[2]` |
| Procurement & Production page | ProcurementAndProductionPage | Asset Depreciation' tab | assetDepreciationTab | BaseWebElement | `//*[@role='dialog' and @aria-hidden='false']//*[@role='tablist' and @aria-hidden='false']//*[@role='tab' and @aria-hidden='false']//*[text()='Asset Depreciation']/ancestor::*[@role='tab' and @aria-hidden='false']` |
| Procurement & Production page | ProcurementAndProductionPage | Click here to add' link | addLink | BaseWebElement | `//a[text()='click here to add']` |
| Procurement & Production page | ProcurementAndProductionPage | Confirm Formula, Define Local Parameters' popUp | confirmFormulaDefineLocalPopUp | BaseWebElement | `//*[@role='dialog' and @aria-hidden='false']//*[text()='Confirm Formula, Define Local Parameters']` |
| Procurement & Production page | ProcurementAndProductionPage | Depreciation Method' field | amortizationMethodField | IpeDropdownField | `//*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//input[starts-with(@data-componentid, 'iBEComboBox')]` |
| Procurement & Production page | ProcurementAndProductionPage | Dialog Confirm' button | dialogConfirm | BaseWebElement | `//*[@role='dialog' and @aria-hidden='false']//*[text()='Confirm']` |
| Procurement & Production page | ProcurementAndProductionPage | Dialog Result' input | dialogResultInput | IpeField | `//*[@role='dialog' and @aria-hidden='false']//*[contains(text(),'Result')]/ancestor::label/following-sibling::*//input[@aria-hidden='false']` |
| Procurement & Production page | ProcurementAndProductionPage | Edit Details' menuItem | editDetailsMenuItem | BaseWebElement | `//div[contains(@class, 'x-menu') and @aria-hidden='false']//a[span[text()='Edit Details']]` |
| Procurement & Production page | ProcurementAndProductionPage | End of Depreciation' checkBox | endOfDepreciationCheckBox | IpeCheckBox | `//*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//*[contains(text(),'end') and @data-ref='boxLabelEl']/ancestor::div[@class='x-form-cb-wrap-inner']//span[contains(@class, 'x-form-checkbox')]` |
| Procurement & Production page | ProcurementAndProductionPage | End of Depreciation' datePicker | endOfDepreciationDatePicker | IpeField | `//*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//*[contains(text(),'End') and @data-ref='labelTextEl']/ancestor::div[contains(@class,'iBEDateTimeCls')]//input` |
| Procurement & Production page | ProcurementAndProductionPage | Estimate for Labor' popup | estimateForLaborPopup | BaseWebElement | `//*[contains(text(),'Estimate for')]/ancestor::*[@role='dialog' and @aria-hidden='false']//*[@role='tab' and @aria-selected='true']` |
| Procurement & Production page | ProcurementAndProductionPage | Formula' table | formulaTable | FormulaTable | `//*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel'] and @aria-hidden='false'` |
| Procurement & Production page | ProcurementAndProductionPage | Material & Equipment Burden Rate' parameter | materialEquipmentBurdenRateParameter | BaseWebElement | `//*[@role='dialog' and @aria-hidden='false']//*[text()='Material & Equipment Burden Rate']` |
| Procurement & Production page | ProcurementAndProductionPage | Mfg' table | mfgTable | CostBreakDownTable | `(//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[@class='x-grid-item-container'])[2]` |
| Procurement & Production page | ProcurementAndProductionPage | Opened' menu | openedMenu | MenuItemField | `//*[@data-ref='listEl' and @role='listbox' and @aria-hidden='false']` |
| Procurement & Production page | ProcurementAndProductionPage | Procurement & Production' table | procurementProductionTable | CommonDualTable | `//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-container x-tabpanel-child'))]//div[@class='x-grid-item-container']` |
| Procurement & Production page | ProcurementAndProductionPage | PROCUREMENTANDPRODUCTION_TAB | procurementProductionTab | BaseWebElement | `//a[@aria-selected='true']//span[text()='Procurement & Production']` |
| Procurement & Production page | ProcurementAndProductionPage | Purchased Parts Base' parameter | purchasedPartsBaseParameter | BaseWebElement | `//*[@role='dialog' and @aria-hidden='false']//*[text()='Purchased Parts Base (USD)']` |
| Procurement & Production page | ProcurementAndProductionPage | Regression - General & Admin Rate' parameter | regressionGeneralAdminRateParameter | BaseWebElement | `//*[@role='dialog' and @aria-hidden='false']//*[text()='Regression - General & Admin Rate']` |
| Procurement & Production page | ProcurementAndProductionPage | Start of Depreciation' checkBox | startOfDepreciationCheckBox | IpeCheckBox | `//label[@data-ref='boxLabelEl' and contains(text(), 'start')]/preceding-sibling::span` |
| Procurement & Production page | ProcurementAndProductionPage | Start of Depreciation' datePicker | startOfDepreciationDatePicker | IpeField | `//*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//*[(contains(text(),'start') or contains(text(),'Start')) and @data-ref='labelTextEl']/ancestor::div[contains(@class,'iBEDateTimeCls')]//input` |
| Procurement & Production page | ProcurementAndProductionPage | Test WBS 1' menuItem | testWBS1Menu | MenuItemField | `//*[text()='Test WBS 1']/ancestor::li[@role='option']` |
| Procurement & Production page | ProcurementAndProductionPage | Test WBS 2' menuItem | testWBS2Menu | MenuItemField | `//*[text()='Test WBS 2']/ancestor::li[@role='option']` |
| Procurement & Production page | ProcurementAndProductionPage | Total Value' dropdown | totalValueDropdown | IpeDropdownField | `//*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//input[contains(@id, 'iBEAmount') and contains(@class, 'x-form-field') and @aria-invalid='false']` |
| Proposals list page | ProposalsListPage | first' proposal | firstProposal | BaseWebElement | `//table[1]//tr/td[@data-columnid='quoteSearchText']/div` |
| Proposals list page | ProposalsListPage | PROPOSALS_LABEL | proposalsLabel | BaseWebElement | `//label[text()='Proposals ']` |
| Proposals list page | ProposalsListPage | PROPOSALS_LIST | proposalsList | LongWaitElement | `//*[@role='rowgroup' and not(contains(@class,'x-grid-header-ct')) and not(@aria-busy='true')]` |
| Purchased Part: Estimate - Additional Data | additionalData | QUANTITIES_FIELD_SET | quantitiesFieldSet | BaseWebElement | `//*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//*[@aria-label='Quantities field set' and @aria-hidden='false']` |
| Purchased Part: Estimate - Cost Estimate | costEstimate | COST_ESTIMATE_TABLE | costEstimateTable | WBSTable | `(//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-panel x-tabpanel-child'))]//div[@class='x-grid-item-container'])` |
| Purchased Part: Estimate - Costing Log | costingLog | COSTING_LOG_TEXT_AREA | costingLogTextArea | BaseWebElement | `//*[@role='tab' and @aria-hidden='false' and @aria-selected='true']//*[text()='Costing Log']/ancestor::*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//*[@role='textbox' and @aria-hidden='false' and @aria-readonly='true']` |
| Purchased Part: Estimate - History | history | HISTORY_LIST | historyList | BaseWebElement | `//*[@role='tab' and @aria-hidden='false' and @aria-selected='true']//*[text()='History']/ancestor::*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//*[@role='listbox' and @aria-hidden='false']` |
| Purchased Part: Estimate - Payments | payments | PAYMENTS_TABLE | quantitiesFieldSet | BaseWebElement | `//*[@role='tab' and @aria-hidden='false' and @aria-selected='true']//*[text()='Payments']/ancestor::*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//*[@role='grid' and @aria-hidden='false']` |
| Purchased Part: Estimate - Proposal Requirements | proposalRequirements | PROPOSAL_REQUIREMENTS_TABLE | quantitiesFieldSet | WBSTable | `//*[@role='tab' and @aria-hidden='false' and @aria-selected='true']//*[text()='Proposal Requirements']/ancestor::*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//*[@role='grid' and @aria-hidden='false']` |
| Purchased Part: Estimate - Remarks | remarks | ASSUMPTIONS_LABEL | assumptionsLabel | BaseWebElement | `//*[@role='tab' and @aria-hidden='false' and @aria-selected='true']//*[text()='Remarks']/ancestor::*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//label[text()='Assumptions']` |
| Purchased Part: Estimate - Tags | tags | TAGS_FIELD_SET | quantitiesFieldSet | BaseWebElement | `//*[@role='tab' and @aria-hidden='false' and @aria-selected='true']//*[text()='Tags']/ancestor::*[@role='dialog' and @aria-hidden='false']//*[@role='tabpanel' and @aria-hidden='false']//*[@role='group' and @aria-label='Tags field set']` |
| Sets page | setsPage | Column' header | columnHeader | MenuItemField | `//*[@role='columnheader' and @aria-hidden='false']` |
| Sets page | setsPage | SETS_TAB | setsTab | BaseWebElement | `//a[@aria-selected='true']//span[text()='Sets']` |
| Sets page | setsPage | Sets' table | setsTable | setsTable | `//*[@role='rowgroup' and not(contains(@class,'x-grid-header-ct')) and not(@aria-busy='true')]` |
| Setup page | SetUpPage | BOE copy is happening and Reschedule' warning | boeCopyProjectRescheduleWarningPopUp | LongWaitElement | `//*[text()='Data saved successfully']/ancestor::*[@role='dialog' and @aria-hidden='false']` |
| Setup page | SetUpPage | Business Area' field | businessAreaField | IpeDropdownField | `//input[@placeholder='Select Company']` |
| Setup page | SetUpPage | Capability' dropdown | leadingCapabilityDropdown | BaseWebElement | `//input[@placeholder='Select Company']/parent::div/following-sibling::div` |
| Setup page | SetUpPage | Capability' field | capabilityField | IpeDropdownField | `//input[@placeholder='Select Company']` |
| Setup page | SetUpPage | Client Customer sell-to' dropdown | clientFieldDropdown | IpeDropdownField | `//input[@placeholder='Enter company  name to search or click arrow for list']/parent::div/following-sibling::div` |
| Setup page | SetUpPage | Client Customer sell-to' field | clientField | IpeIdDropdownField | `//input[@placeholder='Enter company  name to search or click arrow for list']` |
| Setup page | SetUpPage | Close' button | closeButton | BaseWebElement | `//span[text()='Close']/ancestor::a` |
| Setup page | SetUpPage | Company Currency' field | companyCurrencyFieldInSetup | IpeDropdownField | `//input[contains(@id,'iBECurrencyCombo')]` |
| Setup page | SetUpPage | Company' field | companyField | IpeDropdownField | `//input[@placeholder='Select Company']` |
| Setup page | SetUpPage | Confirm' button | confirmButton | BaseWebElement | `//span[text()='Confirm']/ancestor::a` |
| Setup page | SetUpPage | Cost Center' field | costCenterField | IpeField | `//tr//span[text()='Cost Center']//following::div[3]//input` |
| Setup page | SetUpPage | COST_STRUCTURE_TAB | costStructureTab | BaseWebElement | `//a[@aria-selected='false']//span[contains(text(),'Cost Structure')]` |
| Setup page | SetUpPage | CRM Opportunity' dropdown | crmFieldDropdown | BaseWebElement | `//tr//span[text()='CRM Opportunity']//following::input[1]/parent::div/following-sibling::div` |
| Setup page | SetUpPage | CRM Opportunity' field | crmField | IpeDropdownField | `//tr//span[text()='CRM Opportunity']//following::input[1]` |
| Setup page | SetUpPage | Customer Currency' dropdown | customerCurrencyDropdown | BaseWebElement | `//*[@placeholder='Default=USD']/parent::div/following-sibling::div` |
| Setup page | SetUpPage | Customer Currency' field | customerCurrencyField | IpeDropdownField | `//*[@placeholder='Default=USD']` |
| Setup page | SetUpPage | Data saved successfully' popUp | dataSavedSuccessfullyPopUp | LongWaitElement | `//*[text()='Data saved successfully']/ancestor::*[@role='dialog' and @aria-hidden='false']` |
| Setup page | SetUpPage | Delete' link | deleteLink | BaseWebElement | `//a[text()='Delete']` |
| Setup page | SetUpPage | Description' field | projectDescriptionField | IpeField | `//input[@placeholder='Enter a brief description of the bid (something you can remember to find it later by)']` |
| Setup page | SetUpPage | Edit Rates' link | editRatesLink | BaseWebElement | `//a[text()='Edit Rates']` |
| Setup page | SetUpPage | Edit Role or Alt Cust' link | editRoleOrAltCustLink | BaseWebElement | `//a[text()='Edit Role/Alt. Cust.']` |
| Setup page | SetUpPage | End' field | selectEndField | IpeEndDateField | `//input[@placeholder='Select End']` |
| Setup page | SetUpPage | Error' dialog | errorDialog | TextElement | `//*[@role='dialog' and @aria-hidden='false' and contains(@class,'x-closable')]` |
| Setup page | SetUpPage | Escalation and Inflammation Rates' table | escalationAndInflammationRatesTable | EscalationInflationRatesTable | `(//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-window-item x-panel-default'))]//div[@class='x-grid-item-container'])[2]` |
| Setup page | SetUpPage | Escalation first' cell | escalationFirstCell | BaseWebElement | `//*[@role='dialog']//*[@class='x-grid-checkcolumn']/ancestor::td[@role='gridcell']` |
| Setup page | SetUpPage | Escalation or Inflation Rates' tab | escalationOrInflationRates | BaseWebElement | `//span[contains(text(),'Escalation or Inflation Rates')]` |
| Setup page | SetUpPage | Force Update Yes' button | forceUpdateYesButton | BaseWebElement | `//*[@role='alertdialog' and @aria-disabled='false']//*[text()='Yes']` |
| Setup page | SetUpPage | Force Update' popUp | forceUpdatePopUp | LongWaitElement | `//*[@role='alertdialog' and @aria-disabled='false']` |
| Setup page | SetUpPage | Full Screen' option | fullScreenOption | BaseWebElement | `//*[@class='tox-tiered-menu']//*[@role='menu']//*[@aria-label='Fullscreen' and @role='menuitem']` |
| Setup page | SetUpPage | Keep Exact Dates' radioButton | keepExactDatesRadioButton | BaseWebElement | `//label[text()='Keep exact dates']/parent::*//input[@aria-hidden='false']` |
| Setup page | SetUpPage | Leading Company Currency' field | companyCurrencyField | IpeField | `//span[text()='Leading Company Currency *']//following::div[3]//input` |
| Setup page | SetUpPage | Leading Company' dropdown | leadingCompanyDropdown | BaseWebElement | `//input[@placeholder='Select Company']/parent::div/following-sibling::div` |
| Setup page | SetUpPage | Leading Company' field | leadingCompanyField | IpeIdDropdownField | `//input[@placeholder='Select Company']` |
| Setup page | SetUpPage | Leading Department' field | leadingDepartmentField | IpeDropdownField | `//input[@placeholder='Select Department']` |
| Setup page | SetUpPage | Leading Site or Department' field | leadingSiteOrDepartmentField | IpeIdDropdownField | `//input[@placeholder='Select Department']` |
| Setup page | SetUpPage | Leading Site' dropdown | leadingSiteDropdown | BaseWebElement | `//input[@placeholder='Select Department']/parent::div/following-sibling::div` |
| Setup page | SetUpPage | Leading Site' field | leadingSiteField | IpeDropdownField | `//input[@placeholder='Select Department']` |
| Setup page | SetUpPage | Leading Site' menuItem | leadingSiteMenuitem | MenuItemField | `//*[@data-ref='listWrap']/ul[@class='x-list-plain' and @aria-hidden='false']` |
| Setup page | SetUpPage | Maintain Proposal-specific Currency Exchange Rates' popup | exchangePopup | BaseWebElement | `//*[@role='dialog' and @aria-hidden='false']//*[contains(text(),'Exchange') and @data-ref='textEl']` |
| Setup page | SetUpPage | Maintain Proposal-specific Currency Exchange Rates' popup | currencyExchangeRatesPopup | BaseWebElement | `//*[@aria-hidden='false' and @role='dialog']//*[text()='Maintain Proposal-specific Currency Exchange Rates']` |
| Setup page | SetUpPage | Model' field | modelField | IpeDropdownField | `//*[text()='Model']/ancestor::td[1]//input` |
| Setup page | SetUpPage | Model' option | modelOption | BaseWebElement | `//*[@role='listbox' and @aria-hidden='false']//*[contains(text(),'Model')]` |
| Setup page | SetUpPage | Normal' option | normalOption | BaseWebElement | `//*[@class='tox-tiered-menu']//*[@role='menu']//*[@aria-label='Normal' and @role='menuitem']` |
| Setup page | SetUpPage | Open Project in SAP' link | openProjectInSapLink | BaseWebElement | `//a[@class='x-ibeLinksCls ' and text()='Open Project in SAP']` |
| Setup page | SetUpPage | Planned Start' field | selectStartField | IpeField | `//input[@placeholder='Select Start']` |
| Setup page | SetUpPage | Plant or Site' field | plantOrSiteField | IpeDropdownField | `//input[@placeholder='Select Department']` |
| Setup page | SetUpPage | Pricing' tab | pricingTab | BaseWebElement | `//a[@aria-hidden='false' and @role='tab']//span[text()='Pricing']` |
| Setup page | SetUpPage | Profit Center' dropdown | profitCenterDropdown | BaseWebElement | `//tr//span[text()='Profit Center']//following::div[4]/parent::div/following-sibling::div` |
| Setup page | SetUpPage | Profit Center' field | profitCenterField | IpeDropButton | `//tr//span[text()='Profit Center']//following::div[4]` |
| Setup page | SetUpPage | Project Currency' field | projectCurrencyField | IpeDropdownField | `//input[@placeholder='Default=USD']` |
| Setup page | SetUpPage | Project End' field | projectEndField | IpeEndDateField | `//input[@placeholder='Select End']` |
| Setup page | SetUpPage | Project Goals or Remarks' field | projectGoalsOrRemarksField | IPEIFrameElement | `//iframe[@title='Rich Text Area']` |
| Setup page | SetUpPage | Project Goals or Remarks' textbox | projGoalRemTextBox | BaseWebElement | `//iframe[@class='tox-edit-area__iframe']` |
| Setup page | SetUpPage | Project Start' field | projectStartField | IpeField | `//input[@placeholder='Select Start']` |
| Setup page | SetUpPage | Project Type' field | projectTypeFieldInSetup | IpeDropdownField | `//input[contains(@id,'iBEBusObjType')]` |
| Setup page | SetUpPage | Proposal Authorization#' field | proposalAuthorizationField | IpeField | `//*[text()='Proposal Authorization#']/ancestor::td[1]//input` |
| Setup page | SetUpPage | proposal elements to create or copy' radioButtons | proposalElementsToCreateOrCopy | BaseWebElement | `//label[contains(translate(text(), 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz'), 'select a template')]` |
| Setup page | SetUpPage | Proposal Number' in Proposal Header | proposalNumberInProposalHeader | TextElement | `//*[@class='x-component x-PricingAppActivityTitleText x-box-item x-component-default'][last()][contains(text(),'(T5-') and contains(text(),')')]` |
| Setup page | SetUpPage | Proposal Type' field | projectTypeField | IpeDropdownField | `//input[contains(@id,'iBEBusObjType')]` |
| Setup page | SetUpPage | REGTAG' field | regTagField | IpeDropdownField | `//*[@role='tabpanel' and @aria-hidden='false']//*[text()='REGTAG']/ancestor::td[1]//input[@aria-hidden='false']/parent::*` |
| Setup page | SetUpPage | REGTAG' option | regTagOption | BaseWebElement | `//*[@role='listbox' and @aria-hidden='false']//*[contains(text(),'REGTAG')]` |
| Setup page | SetUpPage | Reschedule Project' window | rescheduleProjectWindow | BaseWebElement | `//div[contains(@class, 'x-window')]//*[text()='Reschedule Project']` |
| Setup page | SetUpPage | Reschedule' button | rescheduleButton | BaseWebElement | `//a[@role='button' and @aria-hidden='false']//span[text()='Reschedule']` |
| Setup page | SetUpPage | Reset all Rates to 1' option | ratesTo1Option | BaseWebElement | `//*[text()='Turn-off Escalation (Reset all Rates to 1)']/ancestor::a` |
| Setup page | SetUpPage | Reset dates to' radio | resetDatesToRadio | IpeRadioButton | `//label[contains(text(),'Reset dates to')]` |
| Setup page | SetUpPage | Rich Text Editor View Dropdown' button | richTextEditorViewDropdown | BaseWebElement | `//button[@data-mce-name='view']//div` |
| Setup page | SetUpPage | SAP Project' field | sapProjectField | IpeIdDropdownField | `//a[text()='Open Project in SAP']/ancestor::td[1]//input` |
| Setup page | SetUpPage | Save Confirmation' popUp | saveConfirmationPupUp | LongWaitElement | `//*[@role='dialog' and @aria-disabled='false']//*[text()='Save Confirmation']` |
| Setup page | SetUpPage | Search Box' field | searchBoxField | IpeTemplateDropdownField | `//input[@role='combobox' and @aria-hidden='false' and contains(@data-componentid, 'iBESearchComboBox')]` |
| Setup page | SetUpPage | Search for historical projects to copy' field | historicalProjectsFromLibraryField | IpeTemplateDropdownField | `//tr//span[text()='Search for historical projects to copy']//following::input[1]` |
| Setup page | SetUpPage | Search for Template from Library' field | templateFromLibraryField | IpeTemplateDropdownField | `//tr//legend//*[contains(text(),'Template')]/ancestor::td//input[@role='combobox']` |
| Setup page | SetUpPage | Select a prior bid' radio | priorProposalRadio | IpeRadioButton | `//label[text()='Select a prior bid']` |
| Setup page | SetUpPage | Select a prior project' radio | priorProposalProject | IpeRadioButton | `//label[text()='Select a prior project' or text()='Select a prior proposal' or text()='Select a Prior Project']` |
| Setup page | SetUpPage | Select a prior proposal' dropdown | priorProposalDropdown | BaseWebElement | ` //*[@role='tabpanel' and @aria-hidden='false']//*[contains(@class,'x-field x-iBECombo') and contains(@id,'iBESearchComboBox') and not(contains(@style, 'display: none'))]//*[contains(@class,'x-form-arrow-trigger-default')]` |
| Setup page | SetUpPage | Select a prior proposal' options | priorProposalOptions | BaseWebElement | `//*[@role='listbox' and @aria-hidden='false']//li[@role='option'][1]` |
| Setup page | SetUpPage | Select a prior proposal' radio button | priorProposalRadioButton | IpeRadioButton | `//label[text()='Select a prior proposal']/preceding-sibling::*//input` |
| Setup page | SetUpPage | Select a Template' drop down | selectTemplateDropDown | BaseWebElement | `//*[@role='tabpanel' and @aria-hidden='false']//*[@data-ref='triggerWrap' and contains(@id,'iBESearchComboBox')]//*[contains(@class,'x-form-arrow-trigger-default')]` |
| Setup page | SetUpPage | Select a Template' options | selectTemplateOptions | BaseWebElement | `//*[@role='listbox' and @aria-hidden='false']//li[@role='option'][1]` |
| Setup page | SetUpPage | Select Tag to Add' drop down | selectTagToAddDropDown | BaseWebElement | `//*[@placeholder='Select Tag to Add' and @aria-hidden='false']/parent::div/following-sibling::div` |
| Setup page | SetUpPage | Select Tag to Add' field | selectTagToAddField | IpeDropdownField | `//*[@placeholder='Select Tag to Add' and @aria-hidden='false']` |
| Setup page | SetUpPage | Select Tag to Add' options | selectTagToAddOptions | BaseWebElement | `//*[@role='listbox' and @aria-hidden='false']//li[@role='option'][1]` |
| Setup page | SetUpPage | SETUP_TAB | setupTab | LongWaitElement | `//a[@aria-selected='true']//span[contains(text(),'Setup') or contains(text(),'Opportunity Details')]` |
| Setup page | SetUpPage | Several' radio | severalRadio | IpeRadioButton | `//label[text()='Several']` |
| Setup page | SetUpPage | Single phase' radio | singlePhaseRadio | IpeRadioButton | `//label[text()='Single phase']` |
| Setup page | SetUpPage | Text edit' toolbox | textEditToolbox | BaseWebElement | `//*[@data-alloy-vertical-dir='toptobottom' and @style='display: block;']` |
| Setup page | SetUpPage | Title or Brief Description' field | titleOrBriefDescriptionField | IpeField | `//input[@placeholder='Enter a brief description of the bid (something you can remember to find it later by)']` |
| Setup page | SetUpPage | Update Rates' button | updateRatesButton | BaseWebElement | `//a[@aria-hidden='false']//*[text()='Update Rates']/ancestor::a` |
| Setup page | SetUpPage | Upload RFx Files' button | uploadRfxFiles | BaseWebElement | `//a[contains(@id,'iBEFileUploadButton')]` |
| Setup page | SetUpPage | Yes and Update Costs' button | yesAndUpdateCostsButton | BaseWebElement | `//*[@role='dialog' and @aria-disabled='false']//*[text()='Yes and Update Costs']` |
| Setup page | SetUpPage | Yes' button | yesButton | BaseWebElement | `//*[@role='dialog' and @aria-disabled='false']//*[text()='Yes']` |
| Setup page | SetUpPage | Your Company' field | yourCompanyField | IpeDropdownField | `//*[@placeholder='Select Company']` |
| WBS Cost Structure page | CostStructurePage | 1 Overall Proposal' expander | overallProposalExpander | BaseWebElement | `//*[@role='treegrid' and @aria-hidden='false']//*[@class='x-grid-item-container']//*[contains(text(),'Overall')]/ancestor::*[@role='gridcell']//*[contains(@class,'x-tree-expander')]` |
| WBS Cost Structure page | CostStructurePage | Assign Existing SAP Project ID' popup | assignExistingSAPProjectIDPopup | BaseWebElement | `//*[@role='dialog' and @aria-hidden='false']//*[@role='presentation' and text()='Assign Existing SAP Project ID']` |
| WBS Cost Structure page | CostStructurePage | Close' button | closeButton | BaseWebElement | `//*[@role='dialog' and not(contains(@class,'x-window-ghost')) and @aria-hidden='false']//*[text()='Close']/ancestor::*[@role='button' and @aria-hidden='false']` |
| WBS Cost Structure page | CostStructurePage | Collapse All' menuItem | collapseAllMenuItem | BaseWebElement | `//*[@role='menu' and @aria-hidden='false' and @aria-expanded='true']//*[@role='menuitem' and @aria-hidden='false']//*[text()='Collapse All']` |
| WBS Cost Structure page | CostStructurePage | COST_STRUCTURE_TAB | costStructureTab | BaseWebElement | `//a[@aria-selected='true']//span[contains(text(),'Cost Structure')]` |
| WBS Cost Structure page | CostStructurePage | Expand All' menuItem | expandAllMenuItem | BaseWebElement | `//*[@role='menu' and @aria-hidden='false' and @aria-expanded='true']//*[@role='menuitem' and @aria-hidden='false']//*[text()='Expand All']` |
| WBS Cost Structure page | CostStructurePage | Expand and Collapse' menuItem | expandAndCollapseMenuItem | BaseWebElement | `//*[@role='menu' and @aria-hidden='false' and @aria-expanded='true']//*[@role='menuitem' and @aria-hidden='false']//*[text()='Expand/Collapse']` |
| WBS Cost Structure page | CostStructurePage | Gear' menuItems | descriptionGearMenuItems | MenuItemField | `//*[@role='menu' and @aria-hidden='false' and @aria-expanded='true']` |
| WBS Cost Structure page | CostStructurePage | Select an SAP Project to assign to all WBS elements' field | selectAnSAPProjectToAssignToAllWBSElementsField | IpeDropdownField | `//*[@role='dialog' and @aria-hidden='false']//input[@placeholder='SAP Project' and @aria-hidden='false']` |
| WBS Cost Structure page | CostStructurePage | WBS Cost Structure' table | wbsCostStructureTable | WBSTable | `//*[@role='treegrid' and @aria-hidden='false']//*[@class='x-grid-item-container']` |
| WBS page | WBSPage | 1 Prof Services Estimate' WP | profServicesEstimateWP | BaseWebElement | `//*[text()='1 Prof Services Estimate ']/ancestor::table//*[@data-columnid='quoteWBSTreeWkPackageColumn']//span` |
| WBS page | WBSPage | Add Below' option | addBelowOption | BaseWebElement | `//*[@role='menu' and @aria-hidden='false']//*[contains(text(),'Below')]` |
| WBS page | WBSPage | Add Current Level' option | addCurrentLevelOption | BaseWebElement | `//*[@role='menu' and @aria-hidden='false']//*[contains(text(),'Current Level')]` |
| WBS page | WBSPage | Add Row' button | addRowButton | BaseWebElement | `//div[@data-qtip='Add Row']` |
| WBS page | WBSPage | Analysis' tab | analysisTab | BaseWebElement | `//span[text()='Analysis' or text()='Workbench']` |
| WBS page | WBSPage | Cog' menu | cogMenu | CogMenu | `//table[contains(@class, 'x-grid-item-selected')]//span[@class='x-tree-node-text ']//div[contains(@class, 'ibeCallOut')]` |
| WBS page | WBSPage | Confirm & Open' button | confirmOpenButton | BaseWebElement | `//span[text()='Confirm & Open']` |
| WBS page | WBSPage | Confirm & Release' button | confirmReleaseButton | BaseWebElement | `//span[text()='Confirm & Release']` |
| WBS page | WBSPage | Cost & Price' status | costPriceStatus | TextElement | `//*[@role='tabpanel' and @aria-hidden='false']//*[@class='x-ibeLinksCls']/parent::div/div[1]` |
| WBS page | WBSPage | Estimate' popUp | estimatePopUp | BaseWebElement | `//*[text()='Estimate']/ancestor::*[@role='dialog' and @aria-hidden='false']` |
| WBS page | WBSPage | Margin as Percentage' widget | marginAsPercentageWidget | TextElement | `//*[@role='tabpanel' and @aria-hidden='false']//*[@data-qtip='Margin as Percentage']` |
| WBS page | WBSPage | Margin' widget | marginWidget | TextElement | `//*[@role='tabpanel' and @aria-hidden='false']//*[@data-qtip='Margin as Percentage']` |
| WBS page | WBSPage | Needs Refresh' status | needsRefreshStatus | LongWaitElement | `//*[text()='Needs Refresh']` |
| WBS page | WBSPage | No' button | noButton | BaseWebElement | `//a//span[text()='No']` |
| WBS page | WBSPage | Owner' field | ownerField | IpeDropButton | `//span[text()='Owner *']//following::div[4]` |
| WBS page | WBSPage | Perform Copy' window | performCopy | BaseWebElement | `//div[contains(@class,'x-window-header')]//div[text()='Perform Copy?']` |
| WBS page | WBSPage | Refresh' button | refreshButton | BaseWebElement | `//*[@data-ref='btnIconEl' and contains(@class,'fa-sync-alt ')]` |
| WBS page | WBSPage | Release Basis of Estimate' window | releaseEstimateWindow | BaseWebElement | `//div[contains(@class, 'x-window')]//*[text()='Release Basis of Estimate']` |
| WBS page | WBSPage | Setup' tab | setupTab | BaseWebElement | `//span[text()='Setup']` |
| WBS page | WBSPage | Status & logs' link | statusLogsLink | TextElement | `//*[@role='tabpanel' and @aria-hidden='false']//*[@subtitlelink='showJobStatus']` |
| WBS page | WBSPage | Status' widget | statusWidget | BaseWebElement | `//*[@role='tabpanel' and @aria-hidden='false']//*[@class='x-ibeLinksCls']/ancestor::*[contains(@class,'x-container-default x-scroller')]` |
| WBS page | WBSPage | Total Cost' widget | totalCostWidget | TextElement | `//*[@role='tabpanel' and @aria-hidden='false']//*[@data-qtip='Total cost in the WBS']` |
| WBS page | WBSPage | Total Price' widget | totalPriceWidget | TextElement | `//*[@role='tabpanel' and @aria-hidden='false']//*[@data-qtip='Total Price' or @data-qtip='Total Revenue']` |
| WBS page | WBSPage | Total Revenue' widget | totalRevenueWidget | TextElement | `//*[@role='tabpanel' and @aria-hidden='false']//*[@data-qtip='Total Revenue']` |
| WBS page | WBSPage | Update Cost & Prices' link | updateCostPriceLink | BaseWebElement | `//*[@role='tabpanel' and @aria-hidden='false']//*[@titlelink='updateCostsWithFormula' or @titlelink='updateBoeCostsWithFormula']` |
| WBS page | WBSPage | Update' window | updateWindow | BaseWebElement | `//div[contains(@class,'x-window-header')]//div[text()='Force Update of all Costs - Important']` |
| WBS page | WBSPage | Updated' status | updatedStatus | LongWaitElement | `//*[text()='Updated']` |
| WBS page | WBSPage | WBS | wbsTable | WBSTable | `//div[@class='x-grid-item-container' and not(contains(@style, 'transform'))]` |
| WBS page | WBSPage | WBS_TAB | wbsTab | BaseWebElement | `//a//span[text()='WBS Structure' or contains(text(),'Cost Structure') or contains(text(),'Workstreams')]` |
| WBS page | WBSPage | WBS' tree | wbsTree | SimpleTree | `//div[contains(@class,'x-tree-view x-fit-item x-tree-view-default x-scroller')]` |
| WBS page | WBSPage | Workbench' tab | workbenchTab | BaseWebElement | `//span[text()='Workbench']` |
| WBS page | WBSPage | Yes' button | yesButton | BaseWebElement | `//a//span[text()='Yes']` |
| Workbench page | CostPriceAnalysisWorkbench | Apply & Close' button | applyAndCloseButton | BaseWebElement | `//span[text()='Apply & Close']` |
| Workbench page | CostPriceAnalysisWorkbench | Apply' button | applyButton | BaseWebElement | `//span[text()='Apply']` |
| Workbench page | CostPriceAnalysisWorkbench | CashFlow' tab | cashFlowTab | BaseWebElement | `//span[text()='CashFlow']` |
| Workbench page | CostPriceAnalysisWorkbench | Change Rows & Columns' window | changeWindow | BaseWebElement | `//div[@class='x-panel x-docked x-panel-default x-docked-right x-panel-docked-right x-panel-default-docked-right x-unselectable' and not(contains(@style,'display: none'))]//*[text()='Change Rows & Columns']` |
| Workbench page | CostPriceAnalysisWorkbench | Cog Settings' button | cogMenu | WorkbenchDropButton | `//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-tabpanel-child'))]//a[@data-qtip='Settings']` |
| Workbench page | CostPriceAnalysisWorkbench | Cost Element' menu item | costElementsMenuItem | MenuItemField | `//div[contains(@class,'x-pivot-grid-config-column x-unselectable x-box-item x-pivot-grid-config-column-default')]//span[@data-qtip='Cost Element']` |
| Workbench page | CostPriceAnalysisWorkbench | Cost:Company' menu item | costMenuItem | MenuItemField | `//div[contains(@class,'x-pivot-grid-config-column x-unselectable x-box-item x-pivot-grid-config-column-default')]//span[@data-qtip='Cost: (Company)']` |
| Workbench page | CostPriceAnalysisWorkbench | Description of Cost' menu item | descriptionResourceMenuItem | MenuItemField | `//div[contains(@class,'x-pivot-grid-config-column x-unselectable x-box-item x-pivot-grid-config-column-default')]//span[contains(@data-qtip,'Description of cost') and @data-ref='textCol']/preceding-sibling::*[@data-ref='sortCol']` |
| Workbench page | CostPriceAnalysisWorkbench | Description' filter | descriptionFilter | IpeField | `//*[@data-ref='inputEl' and @aria-hidden='false' and @role='textbox']` |
| Workbench page | CostPriceAnalysisWorkbench | Direct Other materials' expander | otherMaterialsExpander | BaseWebElement | `//*[@role='grid' and @aria-disabled='false']//*[contains(@class,'x-grid-scrollbar-clipper-locked')]//*[@class='x-grid-item-container']//*[text()='Other_materials']/ancestor::table//tr//td[1]` |
| Workbench page | CostPriceAnalysisWorkbench | Direct Other materials' options | otherMaterialOptions | BaseWebElement | `//*[@role='grid' and @aria-disabled='false']//*[contains(@class,'x-grid-scrollbar-clipper-locked')]//*[@class='x-grid-item-container']//*[text()='Other_materials']/ancestor::table//tr//td[not(contains(@class,'x-pivot-grid-group-header-collapsed'))]` |
| Workbench page | CostPriceAnalysisWorkbench | direct' expander | directExpander | BaseWebElement | `(//*[@role='grid' and @aria-disabled='false']//*[contains(@class,'x-grid-scrollbar-clipper-locked')]//*[@class='x-grid-item-container']//*[text()='direct' or text()='Direct Cost' or text()='Direct cost'])[last()]/ancestor::table//tr//td[1]` |
| Workbench page | CostPriceAnalysisWorkbench | direct' options | directOptions | BaseWebElement | `(//*[@role='grid' and @aria-disabled='false']//*[contains(@class,'x-grid-scrollbar-clipper-locked')]//*[@class='x-grid-item-container']//*[text()='direct' or text()='Direct Cost' or text()='Direct cost'])[last()]/ancestor::table//tr//td[not(contains(@class,'x-pivot-grid-group-header-collapsed'))]` |
| Workbench page | CostPriceAnalysisWorkbench | Hours' menu item | hoursMenuItem | MenuItemField | `//div[contains(@class,'x-pivot-grid-config-column x-unselectable x-box-item x-pivot-grid-config-column-default')]//span[@data-qtip='Hours']` |
| Workbench page | CostPriceAnalysisWorkbench | Inflation Other materials' expander | inflationOtherMaterialsExpander | BaseWebElement | `(//*[@role='grid' and @aria-disabled='false']//*[contains(@class,'x-grid-scrollbar-clipper-locked')]//*[@class='x-grid-item-container']//table[.//*[text()='Other_materials']])[2]` |
| Workbench page | CostPriceAnalysisWorkbench | Inflation Other materials' options | inflationOtherMaterialOptions | BaseWebElement | `//*[@role='grid' and @aria-disabled='false']//*[contains(@class,'x-grid-scrollbar-clipper-locked')]//*[@class='x-grid-item-container']//*[text()='Other_materials']/ancestor::table//tr//td[not(contains(@class,'x-pivot-grid-group-header-collapsed'))]` |
| Workbench page | CostPriceAnalysisWorkbench | inflation' expander | inflationExpander | BaseWebElement | `//*[@role='grid' and @aria-disabled='false']//*[contains(@class,'x-grid-scrollbar-clipper-locked')]//*[@class='x-grid-item-container']//*[text()='inflation' or text()='Inflation or Escalation']/ancestor::table//tr//td[1]` |
| Workbench page | CostPriceAnalysisWorkbench | inflation' options | inflationOptions | BaseWebElement | `//*[@role='grid' and @aria-disabled='false']//*[contains(@class,'x-grid-scrollbar-clipper-locked')]//*[@class='x-grid-item-container']//*[text()='inflation' or text()='Inflation or Escalation']/ancestor::table//tr//td[not(contains(@class,'x-pivot-grid-group-header-collapsed'))]` |
| Workbench page | CostPriceAnalysisWorkbench | Not Assigned' expander | notAssignedExpander | BaseWebElement | `//*[@role='grid' and @aria-disabled='false']//*[contains(@class,'x-grid-scrollbar-clipper-locked')]//*[@class='x-grid-item-container']//table[@data-recordindex='0']//*[contains(@class,'x-pivot-grid-group-total')]//td[1]` |
| Workbench page | CostPriceAnalysisWorkbench | Not Assigned' options | notAssignedOptions | BaseWebElement | `//*[@role='grid' and @aria-disabled='false']//*[contains(@class,'x-grid-scrollbar-clipper-locked')]//*[@class='x-grid-item-container']//table[@data-recordindex='0']//*[contains(@class,'x-pivot-grid-group-total')]//td[not(contains(@class,'x-pivot-grid-group-header-collapsed'))]` |
| Workbench page | CostPriceAnalysisWorkbench | Pricing Revenue only' delete button | deleteRevenueButton | BaseWebElement | `//div[text()='Pricing Revenue only']//following-sibling::div[@class='x-tagfield-item-close']` |
| Workbench page | CostPriceAnalysisWorkbench | View' dropdown | viewDropdown | IpeViewDropdownField | `//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-tabpanel-child'))]//a[contains(@class, 'x-split-button')][@aria-hidden='false']//span[contains(text(), 'View')]` |
| Workbench page | CostPriceAnalysisWorkbench | WORKBENCH_TAB | workbenchTab | BaseWebElement | `//a//span[text()='Workbench' or text()='Analysis']` |
| Workbench page | CostPriceAnalysisWorkbench | Workbench' table | workbenchTable | WorkbenchTable | `//div[not(contains(@class,'x-hidden-offsets')) and (contains (@class, 'x-tabpanel-child'))]//div[@class='x-grid-item-container']` |
