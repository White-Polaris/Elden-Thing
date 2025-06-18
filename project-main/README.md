# FIT2099 Assignment (Semester 2, 2024)

```
`7MM"""YMM  `7MMF'      `7MM"""Yb. `7MM"""YMM  `7MN.   `7MF'    MMP""MM""YMM `7MMF'  `7MMF'`7MMF'`7MN.   `7MF' .g8"""bgd  
  MM    `7    MM          MM    `Yb. MM    `7    MMN.    M      P'   MM   `7   MM      MM    MM    MMN.    M .dP'     `M  
  MM   d      MM          MM     `Mb MM   d      M YMb   M           MM        MM      MM    MM    M YMb   M dM'       `  
  MMmmMM      MM          MM      MM MMmmMM      M  `MN. M           MM        MMmmmmmmMM    MM    M  `MN. M MM           
  MM   Y  ,   MM      ,   MM     ,MP MM   Y  ,   M   `MM.M           MM        MM      MM    MM    M   `MM.M MM.    `7MMF'
  MM     ,M   MM     ,M   MM    ,dP' MM     ,M   M     YMM           MM        MM      MM    MM    M     YMM `Mb.     MM  
.JMMmmmmMMM .JMMmmmmMMM .JMMmmmdP' .JMMmmmmMMM .JML.    YM         .JMML.    .JMML.  .JMML..JMML..JML.    YM   `"bmmmdPY  
```

This is the shared link for Contribution Logs.
https://docs.google.com/spreadsheets/d/1rmx79c3CeHN8l5uW8r2JRgeO6moCjHAVlCm1SSZPrDo/edit?usp=sharing
REQ 4 Idea:
- New Merchant NPC
    - Allows player to purchase items (such as consumables and even weapons)
    - Uses currency called "gold" only dropped by specific enemy when killed (Boss and Fly)
        - Currency is also dropped by Furnace Golem and Dancing Lion
    - 3 Merchant each in different area (Gravesite Plains, Belurat Tower and Belurat Sewers)
        - Each merchant sells different items (Some might sell weapon arts weapon etc.)
    - Merchant cannot be attacked or damaged by enemies nor player
    - Merchant can run out of items to sell at that rate it will just prompt the user with a message
- Purchase logic is as such:
    - Player starts with 0 gold and must kill enemies that drops "gold"
        - Gold is given to player in:
            - Spirit drops 25 gold
            - Manfly drops 50 gold
            - Furnace Golem drops 150 gold
            - Dancing Lion drops 250 gold
        - As Spirit and Manfly can spawn in certain areas a player can opt to "farm" gold to buy items they want
        - Items pricing is:
            - Great Knife with any weapon art (Quickstep or Lifesteal) is priced at 150 gold
            - Shortsword with any weapon art (Quickstep or Lifesteal) is priced at 250 gold
            - Flasks of Healing/Rejuvenating is priced at 25 (for 5 charges)
            - Shadowtree Fragments is priced at 50 (each)
        - As vendors at different area have a different inventory of items visiting different vendor is required to unlock different item
            - E.g: "Merchant A only sell weapon with Quickstep at Gravesite Plains, Merchant B only sell weapon with Lifesteal at Belurat Sewer"
   
    