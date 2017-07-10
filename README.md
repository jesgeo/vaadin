# vaadin

Development Environment
  Eclipse Neon
    Vaadin Plugin - Eclipse Marketplace
  Java 1.8
  Vaadin 8.0
  
 Run:
  mvn install
  mvn vaadin:compile
  mvn jetty:run


Evaluation

1. Complexity of learning
  easy to learn, lot of tutoials and videos of walthroigh but lot of concepts 
2. Documentation & forums etc.
  lots https://vaadin.com/framework/comparison 
3. Sufficient libraries / widgets / webservices for our needs & usability 
  certainly a plus point, you even integrate GWT and JavaScript widgets in Vaadin. Very rich 
4. Likelihood of longevity - continued support and development 
  hard to say but 150k developers worldwide, although lots of changes from version 7 to 8
5. Compatibility with anonymus 
  builds on GWT, but complexity in integration is unknown
6. Ease of debugging 
  fast compilation on save, debug through eclipse

1.	A multi-tab panel 
  very easy to setup using TabSheet 
2. A table control within a tab - yes
3. The table should have features : 
a. Pagination (preferably lazy-loading) listing page number, record totals, navigation to page, jump to first and last page, etc. 
  has lazy loading with DataProvider 
b. Sorting by any column – yes 
c. Sorting by date, numeric, alphabetic – can be done
d. Custom sorting (ability to override sort-order with custom logic) – can be done by implementing GridSortOrder
e. Dynamic re-sorting without having to re-load page.  – can be done
f. Multi-row selection/highlighting and ability to trigger an action on selected rows - yes
g. Editing of data in cells (ajax updating to server) for list, text and date values. 
  supports inline editing using two way binding
h. Hyperlinks in cells – yes, can have html
i. Control of column widths – yes



