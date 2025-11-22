Task 1: Create Classes
1. Household Class

This class represents each family or home that participates in recycling.

It must contain:

unique ID → used to uniquely identify each household

name → name of the household or owner

address → location of the household

joining date → when the household joined the program (use Java Date & Time API)

▶ Purpose:
To store all important details of every household and keep them protected using encapsulation (private variables + getters/setters).

2. RecyclingEvent Class

This class stores every recycling action done by a household.

It must contain:

Material type → plastic, glass, metal, paper

Weight (kg) → how much they recycled

Date of recycling → when the recycling happened

Eco points earned → calculated based on weight

▶ Purpose:
To hold each recycling entry. Encapsulation ensures the data stays clean and safe.

📌 Task 2: Create Collections
1. HashMap for Households

Use: HashMap<Integer, Household>

Key = unique household ID

Value = Household object

▶ Why HashMap?
Fast search, update, and retrieval based on household ID.

2. ArrayList for Recycling Events

Each household will have many recycling entries.

Use: ArrayList<RecyclingEvent>

▶ Why ArrayList?
Flexible list to store multiple entries in order.

📌 Task 3: Register Households

You must allow the user to:

Enter household details → name, address

Generate or accept a unique ID

Capture the joining date using the Java Date & Time API

Store the household object inside the HashMap

▶ Goal:
Maintain a growing list of participating households.

📌 Task 4: Log Recycling Events

Users will enter:

Material type

Weight (kg)

Date of recycling

Then:

Calculate eco points → 10 points per kg

Update the household’s total points

Add the recycling event to that household’s ArrayList

▶ Example:
Weight = 5 kg → eco points = 50

📌 Task 5: Store Data (File I/O)

You must save:

Household details

Recycling event lists

Using Java File I/O so that:

When program closes → data is saved

When program opens → data can be loaded again

▶ Purpose:
Persistence (long-term storage).

📌 Task 6: Display Records

Program must show:

1. All registered households

List every household with their details.

2. All recycling events of a household

Display the entire ArrayList of events for a selected household.

3. Total weight recycled

Sum of all recycling weights for that household.

4. Total eco points earned

Total points accumulated from all events.

📌 Task 7: Generate Reports
1. Household with highest total points

Compare all households’ total points and show the winner.

2. Total community recycling weight

Add all weights from all households and display the overall recycled amount.

📌 Task 8: Error Handling

You must use try–catch to handle:

Invalid inputs

Negative weight

Empty material type

Invalid dates

Duplicate household IDs

Prevent adding a household ID that already exists.

File read/write errors

If file is missing, corrupted, or unreadable.

▶ Goal:
Make the program safe, stable, and user-friendly.