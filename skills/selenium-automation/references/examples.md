# Mapping Examples

## Manual Test

```text
Title: Approve order
Precondition: User is logged in as approver
Step 1: Open Orders
Expected: Orders list is displayed
Step 2: Search order ORD-123
Expected: Order appears
Step 3: Approve the order
Expected: Status becomes Approved
```

## Normalized YAML

```yaml
test_id: approve-order
title: Approve order
preconditions:
  - User is logged in as approver
test_data:
  order_id: ORD-123
steps:
  - id: 1
    action: Open Orders
    expected: Orders list is displayed
  - id: 2
    action: Search order
    value: ORD-123
    expected: Order appears
  - id: 3
    action: Approve the order
    expected: Status becomes Approved
```

## Step Mapping

```text
Open Orders       -> NavigationPage.openOrders()
Search order      -> OrdersPage.search(orderId)
Approve the order -> OrderDetailsPage.approve()
Status check      -> OrderDetailsPage.shouldHaveStatus("Approved")
```

## Generated Test Shape

```java
@Test
void approveOrder() {
    navigationPage.openOrders();
    ordersPage.search(orderId);
    ordersPage.open(orderId);
    orderDetailsPage.approve();
    orderDetailsPage.shouldHaveStatus("Approved");
}
```
