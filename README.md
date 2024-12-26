# CountryViewer
An Android app that lists all the countries in the world, using Kotlin, RecyclerView, and MVVM Clean architecture.

Programming Exercise
1. Fetch a list of countries in JSON format from this URL:
   https://gist.githubusercontent.com/peymano-
   wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/cou
   ntries.json
2. Display all the countries in a RecyclerView ordered by the position they appear in the JSON. In each
   cell, show the country's "name", "region", "code" and "capital" in this format:
-----------------------------------------------------
| |
| "name", "region" "code" |
| |
| "capital" |
| |
-----------------------------------------------------
The user should be able to scroll through the full list of countries.
Success hints:
Use MVVM (Model View View Model) + clean architecture
The implementation must be robust (i.e., handling errors and edge cases) and support device rotation.
The goal is to write high-quality code for the portion you choose to implement, not the number of
features implemented.
Show your best in this minimal project scope.
Do’s and don’t
• Use coroutines
• For the sake of this coding challenge; do not use compose
