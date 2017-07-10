
# Vaadin

Development Environment
- Eclipse Neon
-- Vaadin Plugin - Eclipse Marketplace
- Java 1.8
- Vaadin 8.0
  
 Run

```sh
$ mvn install
$ mvn vaadin:compile
$ mvn jetty:run
```

# Evaluation

>Complexity of learning 
-- easy to learn, lot of tutoials and videos of walthroigh but lot of concepts 

>Documentation & forums etc.
--lots https://vaadin.com/framework/comparison 

>Sufficient libraries / widgets / webservices for our needs & usability 
--certainly a plus point, you even integrate GWT and JavaScript widgets in Vaadin. Very rich 

>Likelihood of longevity - continued support and development 
--hard to say but 150k developers worldwide, although lots of changes from version 7 to 8

>Compatibility with anonymus 
--builds on GWT, but complexity in integration is unknown

>Ease of debugging 
--fast compilation on save, debug through eclipse

### Thin slice
* A multi-tab panel 
    - very easy to setup using TabSheet 
* A table control within a tab - yes

###### The table should have features:
* Pagination (preferably lazy-loading) listing page number, record totals, navigation to page, jump to first and last page, etc. 
    - has lazy loading with DataProvider 
* Sorting by any column – yes 
* Sorting by date, numeric, alphabetic – can be done
* Custom sorting (ability to override sort-order with custom logic) – can be done by implementing GridSortOrder
* Dynamic re-sorting without having to re-load page.  – can be done
* Multi-row selection/highlighting and ability to trigger an action on selected rows - yes
* Editing of data in cells (ajax updating to server) for list, text and date values. 
  supports inline editing using two way binding
*  Hyperlinks in cells – yes, can have html
* Control of column widths – yes
