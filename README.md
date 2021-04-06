
## Features
* Any visitor can post their name and a comment to the Guestbook.
* All visitors can see a list of every entry in the Guestbook.

## API Specifications
Endpoint    | Method| Request Body| Response
------------|-------|-------------|----------
/guestbook  |  GET  |             |[{"name":"abc", "comment":"abcd"}, ...]
/guestbook  |  POST | {"name":"abc", "comment":""}|




