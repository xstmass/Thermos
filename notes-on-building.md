# Notes on building

## Steps

Download the "files.zip" from:
 https://dev.cyberarmy.co.uk/kcauldron/

Extract the zip to a folder.
Copy the folder to one named "libs", under the top-level folder.

Extract the zip "extra-libs" to the libs folder.

Run this from the repo root:
  `git submodule update --init --recursive`

Run:
    cd travii
    ./make.sh

Build will sort of succeed, but will error on missing libs.
You'll need to manually patch up the libraries.zip when done.

To see which ones are broken:
    cd ..\build\distributions
    find . -size 0 -print

Copy these over from a working folder.


## Notes on Patches

"Fixed CraftServer not compiling"

Two commits in VoidFlame's repo
  https://github.com/CyberdyneCC/Thermos/commit/ccd326a9c1aa3a592f72be5247d061e7165cbb1f
  https://github.com/CyberdyneCC/Thermos/commit/9ff286f6958be9843f36339a5c1a5b8670ed8aa5

This is already fixed via this commit:
  c7cdebe199cceca70daff69bd0d1071d02e429bd
