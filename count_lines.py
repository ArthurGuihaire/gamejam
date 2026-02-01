
import subprocess
from collections import defaultdict

def get_loc_by_author():
    cmd = [
        "git", "log",
        "--numstat",
        "--format=%aN"
    ]

    result = subprocess.run(
        cmd,
        stdout=subprocess.PIPE,
        stderr=subprocess.PIPE,
        text=True,
        check=True
    )

    author = None
    stats = defaultdict(int)

    for line in result.stdout.splitlines():
        if not line.strip():
            continue

        # Author line (no tabs)
        if "\t" not in line:
            author = line.strip()
            continue

        # numstat line: added \t deleted \t filename
        added, deleted, _ = line.split("\t", 2)

        if added.isdigit():
            stats[author] += int(added)

    return stats


if __name__ == "__main__":
    loc = get_loc_by_author()

    print("\nLines of code added per contributor:\n")
    for author, lines in sorted(loc.items(), key=lambda x: x[1], reverse=True):
        print(f"{author:25} {lines}")