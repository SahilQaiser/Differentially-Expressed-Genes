# MutatedGenes (NetBeans)

## Methods Included
* FoldChange
####	- Mean Method
	`Deg[I] = max(Xi,Yi)/min(Xi,Yi)`

		Xi - Mean of ith Gene Expression from 1st input file
		Yi - Mean of ith Gene Expression from 2nd input file

#### * Significance Analysis of MicroArrays
	`Deg[I] = (Xi-Yi)/(s(i)+s0)`

		Xi - Mean of ith Gene Expression from 1st input file
		Yi - Mean of ith Gene Expression from 2nd input file
		s(i) - StandArd Deviation of ith gene Expression from both files
		s0 - 5th Percentile of all s(i)'s

## Methods to be Included
#### * T-Testing
#### * WAD

## Languages
#### * JAVA